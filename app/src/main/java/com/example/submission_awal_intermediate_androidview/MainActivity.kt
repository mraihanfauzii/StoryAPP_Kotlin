package com.example.submission_awal_intermediate_androidview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_awal_intermediate_androidview.authentication.AuthenticationManager
import com.example.submission_awal_intermediate_androidview.authentication.LoginActivity
import com.example.submission_awal_intermediate_androidview.databinding.ActivityMainBinding
import com.example.submission_awal_intermediate_androidview.model.Story
import com.example.submission_awal_intermediate_androidview.stories.DetailActivity
import com.example.submission_awal_intermediate_androidview.stories.StoriesAdapter
import com.example.submission_awal_intermediate_androidview.stories.StoriesViewModel
import com.example.submission_awal_intermediate_androidview.stories.map.MapsActivity
import com.example.submission_awal_intermediate_androidview.stories.upload.UploadActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import com.example.submission_awal_intermediate_androidview.stories.Factory

class MainActivity : AppCompatActivity() {
    private lateinit var authentication: AuthenticationManager
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var binding: ActivityMainBinding
    private val storiesViewModel: StoriesViewModel by viewModels {
        Factory(authentication.getToken(AuthenticationManager.TOKEN).toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AndroidThreeTen.init(this)

        supportActionBar?.title = "StoryApp"
        authentication = AuthenticationManager(this)
        showLoading(true)
        dataNotFound(false)

        val token = authentication.getToken(AuthenticationManager.TOKEN).toString()

        if (token.isNotEmpty()) {
            storiesAdapter = StoriesAdapter()
            storiesViewModel.stories.observe(this) { story ->
                if (story != null) {
                    storiesAdapter.submitData(this.lifecycle, story)
                    showLoading(false)
                } else {
                    dataNotFound(true)
                    Toast.makeText(this@MainActivity, getString(R.string.failed_load_story), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.apply {
            rvStories.layoutManager = LinearLayoutManager(this@MainActivity)
            rvStories.setHasFixedSize(true)
            rvStories.adapter = storiesAdapter
            floatingActionButton.setOnClickListener {
                val upload = Intent(this@MainActivity, UploadActivity::class.java)
                startActivity(upload)
            }
        }

        storiesAdapter.setOnItemClickCallback(object: StoriesAdapter.OnItemCLickCallback {
            override fun onItemClicked(story: Story) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.CREATEDAT, story.createdAt)
                    it.putExtra(DetailActivity.PHOTOURL, story.photoUrl)
                    it.putExtra(DetailActivity.NAME, story.name)
                    it.putExtra(DetailActivity.STORYDESCRIPTION, story.description)
                    startActivity(it)
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun dataNotFound(state: Boolean) {
        binding.dataKosong.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.profile -> {
                val profile = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(profile)
            }
            R.id.map -> {
            val map = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(map)
            }
            R.id.logOut -> {
                authentication.logOut()
                val logout = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(logout)
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
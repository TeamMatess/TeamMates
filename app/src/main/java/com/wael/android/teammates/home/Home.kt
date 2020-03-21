package com.wael.android.teammates.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.minus
import androidx.databinding.DataBindingUtil
import com.wael.android.teammates.R
import com.wael.android.teammates.databinding.ActivityHomeBinding
import com.wael.android.teammates.home.fragments.TeamsFragment
import java.util.Collections.rotate

//https://android.jlelse.eu/easily-adding-nested-recycler-view-in-android-a7e9f7f04047
class Home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var finalPoint: Point
    private val teams=TeamsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_home)

        binding.enterRoomButton.setOnClickListener {
            binding.roomCode.visibility = View.VISIBLE
            binding.enterRoomButton.visibility = View.INVISIBLE
            val pointOfCreate: Point = getPointOfView(binding.createRoomButton)
            val pointOfLine: Point = getPointOfView(binding.guideline)
            val diffPointsX = pointOfLine.x - pointOfCreate.x
            val diffpointsY = pointOfLine.y - pointOfCreate.y
            finalPoint = Point(diffPointsX, diffpointsY)


            translater(finalPoint)


        }
        binding.createRoomButton.setOnClickListener {
            binding.roomCode.visibility = View.INVISIBLE
            binding.enterRoomButton.visibility = View.VISIBLE
            val pointOfCreate: Point = getPointOfView(binding.createRoomButton)
            val pointOfLine: Point = getPointOfView(binding.guideline)
            val diffPointsX = pointOfLine.x - pointOfCreate.x
            val diffpointsY = pointOfLine.y - pointOfCreate.y
            finalPoint = Point(diffPointsX, diffpointsY)

            translater(finalPoint)
        }
        binding.fragmentBtn.setOnClickListener {
            binding.consa.visibility=View.INVISIBLE
            binding.logo.visibility=View.INVISIBLE
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.contatiner2, teams)
            fragmentTransaction.commit()

        }

    }

    private fun rotater() {
        val animator = ObjectAnimator.ofFloat(
            binding.createRoomButton, View.ROTATION,
            -360f, 0f
        )
        animator.duration = 1000
        disableViewDuringAnimation(binding.enterRoomButton, animator)
        animator.start()
    }

    private fun translater(point: Point) {

        val animator = ObjectAnimator.ofFloat(
            binding.createRoomButton, View.TRANSLATION_Y,
            point.y.toFloat()
        )

        disableViewDuringAnimation(binding.enterRoomButton, animator)
        animator.start()

    }

    fun getPointOfView(view: View): Point {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Point(location[0], location[1])
    }

    private fun disableViewDuringAnimation(view: View, animator: ObjectAnimator) {
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }
}

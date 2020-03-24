package com.wael.android.teammates.home.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Point

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

import com.wael.android.teammates.R
import com.wael.android.teammates.databinding.FragmentFirstHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class FirstHome : Fragment() {
    lateinit var finalPoint: Point

    lateinit var binding: FragmentFirstHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_home, container, false)
       // val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment2) as NavHostFragment
        binding.enterRoomButton.setOnClickListener {
            binding.roomCode.visibility = View.VISIBLE
            binding.enterRoomButton.visibility = View.INVISIBLE
            val pointOfCreate: Point = getPointOfView(binding.createRoomButton)
            val pointOfLine: Point = getPointOfView(binding.guideline)
            val diffPointsX = pointOfLine.x - pointOfCreate.x
            val diffpointsY = pointOfLine.y - pointOfCreate.y
            finalPoint = Point(diffPointsX, diffpointsY)

            binding.createRoomButton.setText("Enter")
            binding.createRoomButton.setOnClickListener { findNavController().navigate(R.id.action_home_to_create_room) }
            translater(finalPoint)


        }
        binding.createRoomButton.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_create_room)

        }

        return binding.root
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

package com.wael.android.teammates.home.data

import com.wael.android.teammates.sign.data.User

class Member(user: User, roomId: String, teamId: String) :
    User(user.id, user.name, user.email, user.password, user.picture, user.phoneNumber) {


}
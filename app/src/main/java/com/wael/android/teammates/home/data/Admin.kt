package com.wael.android.teammates.home.data

import com.wael.android.teammates.sign.data.User

class Admin (user: User,roomId:String):User(user.id,user.name,user.email,user.password,user.picture,user.phoneNumber){


}
package com.example.networking2

data class UserResponse(
	val totalCount:Int?=null,
	val incompleteResults:Boolean?=null,
	val items:List<User>?=null

)


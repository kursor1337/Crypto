package com.kursor.crypto.domain

class ConnectionException(path: String)
    : Exception("Couldn't connect to the path $path")
package com.peiflow.ruedasrarasapp.models

import java.io.Serializable

class EventData :Serializable{
    var name: String? = null
    var description: String? = null
    var locations: MutableList<LatLng>? = null
    var routeUrl: String? = null
    var address: String? = null
    var dateTime: String? = null

    constructor(){}

    constructor(name: String, description:String, locations:MutableList<LatLng>, routeUrl:String, address:String, dateTime:String){
        this.name        = name
        this.description = description
        this.locations   = locations
        this.routeUrl    = routeUrl
        this.address     = address
        this.dateTime    = dateTime
    }
}
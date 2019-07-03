package com.peiflow.ruedasrarasapp.models

import java.io.Serializable

class LatLng : Serializable {
    var lat:Double = 0.0
    var lng:Double = 0.0
    constructor(){}

    constructor(lat:Double, lng:Double)
    {
        this.lat = lat
        this.lng = lng
    }
}
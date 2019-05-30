package com.example.sampleproject.network.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Geo{

    @JsonProperty("lat")
    var lat = ""

    @JsonProperty("lng")
    var lng = ""

}
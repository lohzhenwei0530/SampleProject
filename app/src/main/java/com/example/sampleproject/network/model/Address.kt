package com.example.sampleproject.network.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Address{

    @JsonProperty("street")
    var street = ""

    @JsonProperty("suite")
    var suite = ""

    @JsonProperty("city")
    var city = ""

    @JsonProperty("zipcode")
    var zipcode = ""

    @JsonProperty("geo")
    var geo = Geo()

}
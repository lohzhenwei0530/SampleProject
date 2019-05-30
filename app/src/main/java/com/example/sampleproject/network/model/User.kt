package com.example.sampleproject.network.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class User{

    @JsonProperty("id")
    var id = "0"

    @JsonProperty("name")
    var name = ""

    @JsonProperty("username")
    var username = ""

    @JsonProperty("email")
    var email = ""

    @JsonProperty("address")
    var address = Address()

    @JsonProperty("phone")
    var phone = ""

    @JsonProperty("website")
    var website = ""

    @JsonProperty("company")
    var company = Company()

}
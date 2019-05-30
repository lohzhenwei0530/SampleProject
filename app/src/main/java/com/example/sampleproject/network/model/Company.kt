package com.example.sampleproject.network.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Company{

    @JsonProperty("name")
    var name = ""

    @JsonProperty("catchPhrase")
    var catchPhrase = ""

    @JsonProperty("bs")
    var bs = ""

}

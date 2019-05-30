// var sum = require('./sum');

import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import Vue from 'vue';

var app = new Vue({
    el: '#app',
    data: {
        message: ""
    },
    created: function () {
        // alert("123");
        var that = this;
        $.getJSON("/oil/queryById1", function (result) {
            that.message = result;
        });
    }
});

$("#btn").click(function () {
   alert("123");
   return false;
});
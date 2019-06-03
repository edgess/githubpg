import Vue from 'vue'
import lob from './lob.vue'

$("#btn").click(function () {
    alert("login");
    return false;
});

var vm = new Vue({
    el: '#app',
    data: {
        message: ""
    },
    render: c => c(lob)
});


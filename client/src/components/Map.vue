<template>
  <div>
    <div>
      <br />
    </div>
    <br />
    <gmap-map :center="center" :zoom="12" style="width:50%;  height: 500px;">
      <gmap-marker :position="center"></gmap-marker>
      <gmap-marker
        :key="index"
        v-for="(marker, index) in markers"
        :position="marker.position"
        @click="center = marker.position"
        :icon="markerOptions"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>

<script>
import { getParkingLot } from "../API";

const url = require("../assets/logo.png");

export default {
  name: "Map",
  data() {
    return {
      center: {},
      markers: [],
      places: [],
      currentPlace: null,

      markerOptions: {
        url,
        //size: { width: 60, height: 90, f: "px", b: "px" },
        scaledSize: { width: 60, height: 90, f: "px", b: "px" }
      }
    };
  },

  mounted() {
    this.geolocate();
    getParkingLot();
  },

  methods: {
    // receives a place object via the autocomplete component
    // setPlace(place) {
    //   this.currentPlace = place;
    // },
    // addMarker() {
    //   if (this.currentPlace) {
    //     const marker = {
    //       lat: this.currentPlace.geometry.location.lat(),
    //       lng: this.currentPlace.geometry.location.lng()
    //     };
    //     this.markers.push({ position: marker });
    //     this.places.push(this.currentPlace);
    //     this.center = marker;
    //     this.currentPlace = null;
    //   }
    // },
    geolocate: function() {
      navigator.geolocation.getCurrentPosition(position => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };
      });
    }
  }
};
</script>

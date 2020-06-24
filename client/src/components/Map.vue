<template>
  <div>
    <div>
      <br />
    </div>
    <br />
    <gmap-map :center="center" :zoom="12" style="width:50%;  height: 500px;">
      <gmap-marker :position="center"></gmap-marker>
      <gmap-info-window
        :options="infoOptions"
        :position="infoPosition"
        :opened="infoOpened"
        :content="infoContent"
        @closeclick="infoOpened=false"
      ></gmap-info-window>
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
import db from "../../firebase/init";

const url = require("../assets/logo.png");

export default {
  name: "Map",
  data() {
    return {
      center: {},
      markers: [],
      places: [],
      data: [],
      currentPlace: null,
      markerOptions: {
        url,
        //size: { width: 60, height: 90, f: "px", b: "px" },
        scaledSize: { width: 60, height: 90, f: "px", b: "px" }
      },
      infoPosition: null,
      infoContent: null,
      infoOpened: false,
      infoCurrentKey: null,
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35
        },
        content:
          '<div id="content">' +
          '<div id="siteNotice">' +
          "</div>" +
          '<h1 id="firstHeading" class="firstHeading">Uluru</h1>' +
          '<div id="bodyContent">' +
          "<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large " +
          "sandstone rock formation in the southern part of the " +
          "Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) " +
          "south west of the nearest large town, Alice Springs; 450&#160;km " +
          "(280&#160;mi) by road. Kata Tjuta and Uluru are the two major " +
          "features of the Uluru - Kata Tjuta National Park. Uluru is " +
          "sacred to the Pitjantjatjara and Yankunytjatjara, the " +
          "Aboriginal people of the area. It has many springs, waterholes, " +
          "rock caves and ancient paintings. Uluru is listed as a World " +
          "Heritage Site.</p>" +
          '<p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">' +
          "https://en.wikipedia.org/w/index.php?title=Uluru</a> " +
          "(last visited June 22, 2009).</p>" +
          "</div>" +
          "</div>"
      }
    };
  },

  mounted() {
    this.geolocate();
    db.firestore()
      .collection("parkingLot")
      .orderBy("createdAt", "desc")
      .get()
      .then(snapshot => {
        snapshot.forEach(doc => {
          const position = {
            lat: doc.data().latitude,
            lng: doc.data().longitude
          };
          this.data.push(doc.data());
          this.markers.push({ position });
          // this.infoPosition.push({ position });
        });
        console.log(this.markers);
      });
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

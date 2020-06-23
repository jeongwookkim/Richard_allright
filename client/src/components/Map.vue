<template>
  <div>
    <div>
      <br />
    </div>
    <br />
    <gmap-map :center="center" :zoom="12" style="width:50%;  height: 500px;">
      <gmap-marker
        :key="index"
        v-for="(m, index) in markers"
        :position="m.position"
        @click="center = m.position"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>

<script>
export default {
  name: "Map",
  data() {
    return {
      center: { lat: 37.504279, lng: 127.038158 },
      markers: [],
      // { position: { lat: 37.504279, lng: 127.038158 } }
      places: [],
      currentPlace: null,
    };
  },

  mounted() {
    this.geolocate();
  },
  created() {
    navigator.geolocation.getCurrentPosition((position) => {
      console.log(position);
      this.markers.push({
        position: {
          lat: Number(position.coords.latitude),
          lng: Number(position.coords.longitude),
        },
      });
    });
  },

  methods: {
    // receives a place object via the autocomplete component
    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.markers.push({ position: marker });
        this.places.push(this.currentPlace);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    geolocate: function() {
      navigator.geolocation.getCurrentPosition((position) => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
    },
  },
};
</script>

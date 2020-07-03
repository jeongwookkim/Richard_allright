<template>
  <base-section
    id="parkingLotMap"
    space="0"
  >
    <div>
      <gmap-map
        :options="{
          zoomControl: true,
          mapTypeControl: false,
          scaleControl: false,
          streetViewControl: true,
          rotateControl: false,
          fullscreenControl: true,
          disableDefaultUi: false
        }"
        :center="center"
        :zoom="12"
        style="width:100%;  height: 500px;"
      >
        <gmap-marker :position="center" />

        <gmap-info-window
          :options="infoOptions"
          :position="center"
          :opened="true"
        >
          <p>현재위치</p>
        </gmap-info-window>

        <gmap-marker
          v-for="(marker, index) in markers"
          :key="index"
          :position="marker.position"
          :icon="markerOptions"
          @click="toggleInfo(marker, index)"
        />

        <gmap-info-window
          :options="infoOptions"
          :position="infoPosition"
          :opened="infoOpened"
          @closeclick="infoOpened=false"
        >
          <div
            @click="confirmLogin"
            v-html="infoContent"
          />
        </gmap-info-window>
      </gmap-map>
      <div v-if="terms && isLoggedIn">
        <yaggwan />
      </div>
    </div>
  </base-section>
</template>

<script>
  import { mapMutations, mapGetters, mapActions } from 'vuex'
  import db from '../../../firebase/init'
  import yaggwan from './Yaggwan.vue'

  const url = require('../../assets/location_logo_available.png')

  export default {
    name: 'ParkingLotMap',
    provide: {
      heading: { align: 'center' },
    },
    components: { yaggwan },
    data () {
      return {
        center: {},
        markers: [],
        places: [],

        markerOptions: {
          url,
          // size: { width: 60, height: 90, f: "px", b: "px" },
          scaledSize: { width: 35, height: 55, f: 'px', b: 'px' },
        },

        infoPosition: null,
        infoContent: null,
        infoOpened: false,
        infoCurrentKey: null,
        infoOptions: {
          pixelOffset: {
            width: 0,
            height: -35,
          },
        },
      }
    },
    computed: {
      ...mapGetters(['terms', 'isLoggedIn']),
    },
    mounted () {
      this.geolocate()
      db.firestore()
        .collection('parkingLot')
        .orderBy('createdAt', 'desc')
        .get()
        .then(snapshot => {
          snapshot.forEach(doc => {
            const position = {
              lat: doc.data().latitude,
              lng: doc.data().longitude,
            }
            // this.data.push(doc.data());
            this.markers.push({
              uid: doc.data().uid,
              name: doc.data().name,
              position,
              address: doc.data().address,
              maxNumber: doc.data().maxNumber,
              useNumber: doc.data().useNumber,
              price: doc.data().price,
            })
            // this.infoPosition.push({ position });
          })
        })
    },
    methods: {
      ...mapMutations(['setTerms', 'setpaymentInfo']),
      ...mapActions(['confirmLogin']),
      geolocate: function () {
        navigator.geolocation.getCurrentPosition(position => {
          this.center = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          }
        })
      },
      toggleInfo: function (marker, index) {
        this.infoPosition = marker.position
        this.infoContent = this.getInfoContent(marker)

        if (this.infoCurrentKey === index) {
          this.infoOpened = !this.infoOpened
        } else {
          this.infoOpened = true
          this.infoCurrentKey = index
        }
      },
      getInfoContent: function (marker) {
        this.setpaymentInfo(marker)
        return `
        <div class="card">
          <!-- <div class="card-image">
            <figure class="image is-4by3">
              <img src="https://bulma.io/images/placeholders/96x96.png" alt="Placeholder image">
            </figure>
          </div> -->
          <div class="card-content">
            <div class="media">
              <div class="media-content">
                <p class="title is-4">${marker.name}</p>
              </div>
            </div>
            <div class="content">
            ${marker.address}
              <br>
            </div>
          </div>
        </div>`
      },
    },
  }
</script>

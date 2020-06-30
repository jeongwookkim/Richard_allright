<template>
  <section id="hero">
    <v-img
      :min-height="minHeight"
      :src="require('@/assets/home-hero.jpg')"
      class="white--text"
      gradient="to right, rgba(5, 11, 31, .8), rgba(5, 11, 31, .8)"
    >
      <v-container class="fill-height px-4 py-12">
        <v-responsive
          class="d-flex align-center"
          height="100%"
          max-width="900"
          width="100%"
        >
          <base-heading title="당신이 원하는 공유 주차장 서비스 " />

          <base-body>
            더 이상의 주차난은 없다. Richard All Right
          </base-body>

          <div
            :class="$vuetify.breakpoint.smAndDown ? 'flex-column align-start' : 'align-center'"
            class="d-flex flex-wrap"
          >
            <base-btn>
              INSTALL APP
            </base-btn>
            <!-- <a :href="link">
              <base-btn @click="link">
                INSTALL APP
              </base-btn>
            </a> -->
            <span class="font-weight-bold ml-6 mr-4 my-4">or</span>

            <base-btn
              :ripple="false"
              class="pa-1"
              height="auto"
              text
              @click="$router.push({name : 'ParkingLot'})"
            >
              Get Started Now
            </base-btn>
          </div>
        </v-responsive>
      </v-container>
    </v-img>
  </section>
</template>

<script>
  export default {
    name: 'SectionHero',
    // data() {
    //   return {
    //     link: 'https://play.google.com/store/apps/details?id=com.gihoon.richardallright'
    //   }
    // },
    
    provide: {
      theme: { isDark: true },
   },

    computed: {
      minHeight () {
        const height = this.$vuetify.breakpoint.mdAndUp ? '100vh' : '50vh'

        return `calc(${height} - ${this.$vuetify.application.top}px)`
      },
    },
  }
</script>

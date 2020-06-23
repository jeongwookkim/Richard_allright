<template>
  <div>
    <!-- <h1>컨텐트 컴포넌트</h1> -->
    <v-carousel cycle height="400" hide-delimiter-background="" show-arrows-on-hover="">
      <v-carousel-item v-for="(item,i) in imgItem" :key="i"
        
      >
      <v-sheet :color="colors" height="100%">
        <v-row class="fill-height"
          align="center"
          justify="center">
          <img :src="item.src">
        </v-row>
      </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>

export default {
  name: "content",
  data() {
    return {
      imgItem: [
        {
          src: "/img/page1.png"
        },
        {
          src: "/img/page2.png"
        }
      ],
      colors: 'white',
      slides: [
        'First',
        'Second',
        'Third',
        'Fourth',
        'Fifth'
      ],
    }
  }
}
</script>

<style>
/* img {
  height:500px;
  width:750px;
} */
</style>

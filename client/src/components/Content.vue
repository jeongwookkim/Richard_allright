<template>
  <div>
    <div>
      <!-- <h1>컨텐트 컴포넌트</h1> -->
      <v-carousel cycle height="400" hide-delimiter-background="" show-arrows-on-hover="">
        <v-carousel-item v-for="(item,i) in slideItem" :key="i">
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
    <div>
      <v-container>
        <v-row class="flex-child">
          <v-col cols="12">
            <v-sheet  color="#a8f7de" height="250">
              <!-- <h2 align="center" style="margin-top:50px">Richard all right 은요?</h2> -->
              <ul>
                <p class="d-inline pa-2" v-for="(name, i) in profile" :key="i">{{ name }}</p>
              </ul>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
      
    </div>
  </div>
</template>

<script>

export default {
  name: "content",
  data() {
    return {
      slideItem: [
        { src: "/img/page1.png" },
        { src: "/img/page2.png" }
      ],
      colors: 'white',
      slides: [
        'First',
        'Second',
        'Third',
        'Fourth',
        'Fifth'
      ],
      profile: [
        '김정욱',
        '이기훈',
        '김지환',
        '김민경'
      ]
    }
  }
}
</script>

<style>
</style>

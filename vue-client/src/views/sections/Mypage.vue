<template>
  <base-section
    id="mypage"
    space="0"
  >
    <div>
      <!-- <img style="width" src="/img/page1.png"> -->
      <!-- <v-container
      class="fill-height"
      fluid
      style="min-width: 375px">
    <v-fade-transition mode="out-in">
      <v-row >
        <v-col cols="6"> -->
      <!-- <v-card> -->
      <!-- <v-img
              src="/img/page1.png"
              height="300"
              width="400"
              class="grey darken-4"
            ></v-img> -->
      <!-- <v-card-title class="title">height</v-card-title> -->
      <!-- </v-card> -->
      <!-- </v-col>
      </v-row>
    </v-fade-transition>
      </v-container> -->
      <v-card
        class="mx-auto"
        max-width="344"
        outlined
      >
        <v-img src="/img/page1.png" />
        <v-list-item three-line>
          <v-list-item-content>
            <div class="overline mb-4">
              Profile
            </div>
            <v-list-item-title class="headline mb-1">
              김민경
            </v-list-item-title>
            <v-list-item-subtitle>연락처 : 010-1234-5678</v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-avatar
            tile
            size="80"
            color="grey"
          >
            <v-img src="/img/page1.png" />
          </v-list-item-avatar>
        </v-list-item>

        <v-card-actions>
          <v-btn text>
            회원정보 수정
          </v-btn>
        </v-card-actions>
        <v-card-actions>
          <v-btn
            text
            class="font-weight-bold"
            outlined
            color="#3da886"
          >
            내가 예약한 주차장 찾기
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </base-section>
</template>

<script>
  export default {
    name: 'Mypage',
  }
</script>

<style></style>

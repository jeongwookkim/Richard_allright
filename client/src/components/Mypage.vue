<template>
  <div>
    <v-card
      class="mx-auto"
      max-width="400"
      outlined
    >
      <v-img src="/img/page1.png"></v-img>
      <v-list-item three-line>
        <v-list-item-content>
          <div class="overline mb-4">Profile</div>
          <v-list-item-title class="headline mb-1">김민경</v-list-item-title>
          <v-list-item-subtitle>연락처 : 010-1234-5678</v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-avatar
          tile
          size="80"
          color="grey"
        >
        <v-img src="/img/page1.png"></v-img></v-list-item-avatar>
      </v-list-item>

      <v-card-actions>
        <v-btn text>회원정보 수정</v-btn>
      </v-card-actions>
      <v-card-actions>
        <v-btn text class="font-weight-bold" outlined color="#3da886">내가 예약한 주차장 찾기</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "Mypage",
};
</script>

<style></style>

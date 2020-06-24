<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="550px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="#3da886"
            dark
            v-bind="attrs"
            v-on="on"
          >
            약관보기
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">주차장 이용을 위한 약관에 동의 하시겠습니까?</span>
          </v-card-title>
          <v-card-text>
            약관내용표시
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="disagree">Disagree</v-btn>
            <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "Yaggwan",
  data() {
    return {
      dialog: false,
    }
  },
  methods: {
    disagree(){
      alert("약관에 동의해야만 주차장 이용이 가능합니다.")
      this.dialog="ture";
    }
  }
};
</script>

<style></style>

<template>
  <v-row justify="center">
    <!-- <v-btn color="primary" dark @click.stop="dialog = true">Open Dialog</v-btn> -->

    <v-dialog v-model="terms" max-width="290">
      <v-card>
        <v-card-title class="headline">약관!!!</v-card-title>

        <v-card-text>약관입니다 </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="green darken-1" text @click="setTerms">Disagree</v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="[$router.push('Payment'), setTerms(), setPayment()]"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
export default {
  data() {
    return {
      dialog: false,
    };
  },
  computed: {
    ...mapGetters(["terms", "payment"]),
  },
  methods: {
    ...mapMutations(["setTerms", "setPayment"]),
  },
};
</script>

<style scoped></style>

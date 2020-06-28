<template>
  <base-section id="confirmPay">
    <div>
      <div v-if="payment">
        <h1>Payment Page</h1>
        <p>주차장 이름은 : {{ payInfo.parkingLotName }}</p>
        <p>주차장 주소는 : {{ payInfo.address }}</p>
        <p>주차장 가격은 : {{ payInfo.price }}</p>
        <button @click="payForKakaoPay">
          카카오로 결제하기
        </button>
      </div>
      <div v-else>
        <h1>잘못된 접근 입니다.</h1>
      </div>
    </div>
  </base-section>
</template>

<script>
// import axios from "axios";
  import { mapGetters } from 'vuex'
  export default {
    name: 'Payment',
    data () {
      return {
        payInfo: {},
      }
    },
    computed: {
      ...mapGetters(['payment', 'paymentInfo']),
    },
    created () {
      this.payInfo = this.paymentInfo
    },
    methods: { payForKakaoPay () {} },
  }
</script>

<style></style>

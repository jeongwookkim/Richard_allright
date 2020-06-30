<template>
  <base-section
    v-if="payment"
    id="confirmPay"
  >
    <base-section-heading title="Payment Page">
      <p>주차장 이름은 : {{ payInfo.parkingLotName }}</p>
      <p>주차장 주소는 : {{ payInfo.address }}</p>
      <p>주차장 가격은 : {{ payInfo.price }}</p>
      <button @click="payForKakaoPay">
        카카오로 결제하기
      </button>
    </base-section-heading>
  </base-section>
</template>
<script>
// import axios from "axios";
  import { mapGetters } from 'vuex'
  import router from '../../router'
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
      if (!this.payment) {
        router.push('/')
      }
    },
    methods: { payForKakaoPay () {} },
  }
</script>

<style></style>

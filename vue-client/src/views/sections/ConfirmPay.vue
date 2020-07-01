<template>
  <base-section
    v-if="payment"
    id="confirmPay"
  >
    <base-section-heading title="Payment Page">
      <p>주차장 이름 : {{ payInfo.parkingLotName }}</p>
      <p>주차장 주소 : {{ payInfo.address }}</p>
      <p>주차장 가격 : {{ payInfo.price }}</p>
      <v-btn @click="payForKakaoPay">
        kakaopay로 결제하기
      </v-btn>
    </base-section-heading>
  </base-section>
</template>
<script>
  import axios from 'axios'
  // import rp from 'request-promise'
  import { mapGetters, mapActions } from 'vuex'
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
    methods: {
      ...mapActions(['createTidCookie']),
      payForKakaoPay () {
        const baseUrl = 'http://localhost:3030/payment'
        const data = {
          parkingLotName: this.paymentInfo.parkingLotName,
          price: this.paymentInfo.price,
        }
        const config = {
          withCredentials: true,
        }
        axios.post(baseUrl, data, config)
          .then((response) => {
            this.createTidCookie(response.data.tid)
            location.href = response.data.next_redirect_pc_url
          })
          .catch((err) => {
            console.log(err)
          })
      },
    },
  }
</script>

<style></style>

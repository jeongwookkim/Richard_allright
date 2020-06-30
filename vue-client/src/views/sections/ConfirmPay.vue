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
  // import axios from 'axios'
  import rp from 'request-promise'
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
    methods: {
      payForKakaoPay () {
        // const baseUrl = 'https://kapi.kakao.com/v1/payment/ready'
        // const config = {
        //   headers: {
        //     Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_ADMIN_KEY}`,
        //     'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        //     'Access-Control-Allow-Origin': '*',
        //   },
        //   withCredentials: true,
        //   credentials: 'same-origin',
        // }
        // const data = {
        // cid: 'TC0ONETIME',
        // partner_order_id: '1111', // 주문번호
        // partner_user_id: 'partner_user_id', // 유저 아이디
        // item_name: this.paymentInfo.parkingLotName, // "초코파이", // 메인 주문 상품 외 2건
        // quantity: 1, // 총 갯수
        // total_amount: this.paymentInfo.price, // 2200, // 총액
        // vat_amount: 0,
        // tax_free_amount: 0,
        // approval_url: 'https://modoo.herokuapp.com/pay',
        // fail_url: 'https://www.daum.net',
        // cancel_url: 'https://www.kakao.com',
        // }
        // axios.defaults.withCredentials = true
        // axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*'
        // axios.post(baseUrl, data, config)
        //   .then((response) => {
        //     console.log(response.data)
        //   })
        //   .catch((err) => {
        //     console.log(err)
        //   })

        const options = {
          method: 'POST',
          uri: 'https://kapi.kakao.com/v1/payment/ready',
          form: {
            cid: 'TC0ONETIME',
            partner_order_id: '1111', // 주문번호
            partner_user_id: 'partner_user_id', // 유저 아이디
            item_name: this.paymentInfo.parkingLotName, // "초코파이", // 메인 주문 상품 외 2건
            quantity: 1, // 총 갯수
            total_amount: this.paymentInfo.price, // 2200, // 총액
            vat_amount: 0,
            tax_free_amount: 0,
            approval_url: 'https://modoo.herokuapp.com/pay',
            fail_url: 'https://www.daum.net',
            cancel_url: 'https://www.kakao.com',
          },
          headers: {
            authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_ADMIN_KEY}`,
            'content-type': 'application/x-www-form-urlencoded;charset=utf-8',
          },
        }

        rp(options)
          .then(async (body) => {
            console.log(body)
            // const kakaoResponse = JSON.parse(body)
            // try {
            //   const insertOrder = await Order.create({
            //     quantity,
            //     order_number,
            //     customer_id: userId,
            //     product_id,
            //   })
            //   const increaseCount = await Customer.update(
            //     {
            //       order_count: userOrderCount,
            //     },
            //     { where: { id: userId } },
            //   )
            // } catch (err) {
            //   console.log(err)
            // }
            // res.json({ message: kakaoResponse.next_redirect_pc_url })
          })
          .catch((err) => {
            console.log(err)
          })
      },
    },
  }
</script>

<style></style>

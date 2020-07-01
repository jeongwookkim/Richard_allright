const express = require('express')
const cors = require('cors')
const dotenv = require('dotenv')
const axios = require('axios')
const qs = require('qs')

dotenv.config()

const app = express()

const corsOptions = {
    origin: true,
    credentials: true,
  };

app.use(cors(corsOptions))
app.use(express.json())
app.use(express.urlencoded({extended: true}))

app.post('/payment', (req, res)=>{
    const parkingLotName = req.body.parkingLotName
    const price = req.body.price
    const baseUrl = 'https://kapi.kakao.com/v1/payment/ready'    
    const config = {
        headers: {
            Authorization: `KakaoAK ${process.env.KAKAO_ADMIN_KEY}`,
            'Content-type': 'application/x-www-form-urlencoded;charset=utf-8'
             // 'Access-Control-Allow-Origin': '*',
            },
            // withCredentials: true,
            // credentials: 'same-origin',
        }
    const data = {
        cid: 'TC0ONETIME',
        partner_order_id: '1111', // 주문번호
        partner_user_id: 'partner_user_id', // 유저 아이디
        item_name: parkingLotName, // "초코파이", // 메인 주문 상품 외 2건
        quantity: 1, // 총 갯수
        total_amount: price, // 2200, // 총액
        vat_amount: 0,
        tax_free_amount: 0,
        approval_url: 'https://localhost:8081/approve',
        fail_url: 'https://www.daum.net',
        cancel_url: 'https://www.kakao.com',
        }
        axios.post(baseUrl, qs.stringify(data), config)
        .then((response) => {
            return res.json(response.data.next_redirect_pc_url)
        })
        .catch((err) => {
            console.log(err)
        })
})
app.get('/aaa',())
app.post('/approve', (req, res) => {
    const baseUrl = 'https://kapi.kakao.com/v1/payment/approve'    
    const config = {
        headers: {
            Authorization: `KakaoAK ${process.env.KAKAO_ADMIN_KEY}`,
            },
        }
    const data = {
        cid: 'TC0ONETIME',
        tid: 'T1234567890123456789',
        partner_order_id: 'partner_order_id',
        partner_user_id: 'partner_user_id',
        pg_token: req.body.token.pg_token
        }
        axios.post(baseUrl, qs.stringify(data), config)
        .then((response) => {
            console.log(response)
            return res.json(response.data)
        })
        .catch((err) => {
            console.log(err)
        })
    
})

app.listen(process.env.PORT,()=>{
    console.log(`server listening ${process.env.PORT}`)
})
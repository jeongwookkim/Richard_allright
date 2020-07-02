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
        item_name: parkingLotName, // 주문 상품
        quantity: 1, // 총 갯수
        total_amount: price, // 2200, // 총액
        vat_amount: 0,
        tax_free_amount: 0,
        approval_url: 'http://localhost:8081/approve',
        fail_url: 'http://localhost:8080',
        cancel_url: 'http://localhost:8080',
        }
        axios.post(baseUrl, qs.stringify(data), config)
        .then((response) => {
            // console.log(response.data)
            return res.json(response.data)
        })
        .catch((err) => {
            console.log(err)
        })
})

app.post('/approve', (req, res) => {
    console.log(req.body)
    const baseUrl = 'https://kapi.kakao.com/v1/payment/approve'    
    const config = {
        headers: {
            Authorization: `KakaoAK ${process.env.KAKAO_ADMIN_KEY}`,
            },
        }
    const data = {
        cid: 'TC0ONETIME',
        tid: req.body.tid,
        partner_order_id: '1111',
        partner_user_id: 'partner_user_id',
        pg_token: req.body.pg_token.pg_token
        }
        axios.post(baseUrl, qs.stringify(data), config)
        .then((response) => {
            console.log(response.data)
            return res.json(response.data)
        })
        .catch((err) => {
            console.log(err)
        })
    
})

app.listen(process.env.PORT,()=>{
    console.log(`server listening ${process.env.PORT}`)
})
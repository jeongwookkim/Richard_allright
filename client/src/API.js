import db from "../firebase/init";

export const getParkingLot = () => {
  db.firestore()
    .collection("parkingLot")
    // .orderBy("createdAt", "desc")
    .get()
    .then((snapshot) => {
      console.log(snapshot.docs);
      let data = snapshot.docs.map((doc) => {
        return doc;
      });
      return data;
    });
};

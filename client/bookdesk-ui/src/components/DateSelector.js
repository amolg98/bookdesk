import React, { useState } from "react";  
import DatePicker from "react-datepicker";  

import "../../node_modules/react-datepicker/dist/react-datepicker.css"
  
const DateSelector = () => {  
  const [startDate, setStartDate] = useState("");  
  return (  
    <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />  
  );
};

export default DateSelector;
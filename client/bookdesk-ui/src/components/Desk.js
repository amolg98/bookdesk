import React, { useState, useEffect, useRef } from 'react';

import Form from "react-validation/build/form";
import CheckButton from "react-validation/build/button";

import { DatePicker, TimePicker } from '@mui/lab';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';

import DeskService from "../services/desk.service";

import "../../node_modules/react-datepicker/dist/react-datepicker.css"
import { Paper, Grid, Table, TextField, List, ListItem, ListItemText } from "@mui/material";

const Desk = (props) => {
    const form = useRef();
    const checkBtn = useRef();
    // console.log("In Desk");
    const [content, setContent] = useState("");
    const [loading, setLoading] = useState(false);
    const [deskList, setDeskList] = useState("");
    
    // const dispatch = useDispatch();
    
    const [selectDate, setSelectDate] = useState("");
    const [selectStartTime, setSelectStartTime] = useState("");
    const [selectFinishTime, setSelectFinishTime] = useState("");
    const [currStartTimeStamp, setCurrStartTimeStamp] = useState("");
    const [currFinishTimeStamp, setCurrFinishTimeStamp] = useState("");
    
    const onChangeSelectDate = (date) => {
        setSelectDate(date);
    }

    const onChangeSelectStartTime = (startTime) => {
        setSelectStartTime(startTime);
    }

    const onChangeSelectFinishTime = (finishTime) => {
        setSelectFinishTime(finishTime);
    }

    const onChangeDeskList = (deskList) => {
        setDeskList(deskList);
    }

    const handleDateTimeSubmit = (e) => {  
        e.preventDefault();
        setLoading(true);
        // console.log(selectDate.getDate());
        // console.log(selectStartTime.getHours());
        // console.log(selectStartTime.getMinutes());
        // console.log(selectFinishTime.getHours());
        // console.log(selectFinishTime.getMinutes());

        var csts = selectDate.getFullYear() + "-" + selectDate.getMonth() + "-" + selectDate.getDate() + "T" + ("0" + selectStartTime.getHours()).slice(-2) + ":" + ("0" +selectStartTime.getMinutes()).slice(-2);
        var cfts = selectDate.getFullYear() + "-" + selectDate.getMonth() + "-" + selectDate.getDate() + "T" + ("0" + selectFinishTime.getHours()).slice(-2) + ":" + ("0" +selectFinishTime.getMinutes()).slice(-2);

        // console.log("curr time stamp is: ", currStartTimeStamp);
        // console.log("curr time stamp is: ", currFinishTimeStamp);

        setCurrStartTimeStamp(csts);
        setCurrFinishTimeStamp(cfts);

        let bookingParam = {
            date: "",
            startTimestamp: currStartTimeStamp,
            stopTimestamp: currFinishTimeStamp,
            userId: "",
            deskId: ""
        };

        DeskService.getDesk(bookingParam).then(
            (response) => {
                    console.log("In desk response part", response.data);
                    setContent(response.data);
                    onChangeDeskList(response.data);
                    setSelectDate("");
                    setSelectStartTime("");
                    setSelectFinishTime("");
            },
            (error) => {
                const _content = 
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setContent(_content);
            }
        );
    };

    return(
        <div className="col-md-12">
            <div className="card card-container">
                <Form onSubmit={handleDateTimeSubmit} ref={form}>
                    <div>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DatePicker
                                label="Select Date"
                                value={selectDate}
                                onChange={onChangeSelectDate}
                                renderInput={(params) => <TextField {...params} />} 
                            />
                            <TimePicker
                                label="Select Start Time"
                                // orientation="landscape"
                                value={selectStartTime}
                                onChange={onChangeSelectStartTime}
                                renderInput={(params) => <TextField {...params} />} 
                            />
                            <TimePicker
                                label="Select Finish Time"
                                value={selectFinishTime}
                                onChange={onChangeSelectFinishTime}
                                renderInput={(params) => <TextField {...params} />} 
                            />
                        </LocalizationProvider>
                    </div>
                    <div className="form-group">
                        <button className="btn btn-primary btn-block" disabled={loading}>
                            {/* {loading && (
                                <span className="spinner-border spinner-border-sm"></span>
                            )} */}
                            <span>Get Desks</span>
                        </button>
                    </div>
                    <CheckButton style={{ display: "none" }} ref={checkBtn} />
                </Form>
            </div>
            <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                    <Paper style={{maxHeight: 200, overflow: 'auto'}}>
                        {deskList && deskList.map((desk) => 
                            <ListItem key={desk.id} componentsProps={desk}>{desk.deskNumber}</ListItem>)}
                            <Table />
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
};

export default Desk;
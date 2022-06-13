import React, {useState} from "react";
import "../../../styles/ContentCreator/AddAndUpdateApplicationPage.css";
import axios from 'axios';
import ReactDOM from "react-dom";
import {useParams, useNavigate, Link} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCirclePlus, faPen} from "@fortawesome/free-solid-svg-icons";

function AddApp_creator() {

    let navigate = useNavigate();

    // google api requests
    function SaveArchiveIcon(e) {
        let file = e.target.files[0] //the file
        let reader = new FileReader() //this for convert to Base64
        reader.readAsDataURL(e.target.files[0]) //start conversion...
        reader.onload = function (e) { //.. once finished..
            let rawLog = reader.result.split(',')[1]; //extract only the file data part

            setLoadingIcon();

            let dataSend = {
                dataReq: {data: rawLog, name: file.name, type: file.type},
                fname: "uploadFilesToGoogleDrive"
            }; //preapre info to send to API
            fetch('https://script.google.com/macros/s/AKfycby3Ey1lmmyX9CAsRlanTAU4FveEyfKqnjrYQPTaaBHUEN6Z3OrF/exec', //your AppsScript URL
                {method: "POST", body: JSON.stringify(dataSend)}) //send to Api
                .then(res => res.json()).then((a) => {
                changeIcon(a.url);
                setLoadingIcon("");
            }).catch((e) => {
                setLoadingIcon("Something went wrong please try again later.\n" + e);
            })
        }
    }
    function SaveArchiveImage(e) {
        let file = e.target.files[0] //the file
        let reader = new FileReader() //this for convert to Base64
        reader.readAsDataURL(e.target.files[0]) //start conversion...
        reader.onload = function (e) { //.. once finished..
            let rawLog = reader.result.split(',')[1]; //extract only the file data part

            setLoadingImage("Loading...");

            let dataSend = {
                dataReq: {data: rawLog, name: file.name, type: file.type},
                fname: "uploadFilesToGoogleDrive"
            }; //preapre info to send to API
            fetch('https://script.google.com/macros/s/AKfycby3Ey1lmmyX9CAsRlanTAU4FveEyfKqnjrYQPTaaBHUEN6Z3OrF/exec', //your AppsScript URL
                {method: "POST", body: JSON.stringify(dataSend)}) //send to Api
                .then(res => res.json()).then((a) => {
                AddImage(a.url);
                setLoadingImage("");
            }).catch((e) => {
                setLoadingImage("Something went wrong please try again later.\n" + e);
            })
        }
    }
    function SaveArchiveApp(e){
        let file = e.target.files[0] //the file
        let reader = new FileReader() //this for convert to Base64
        reader.readAsDataURL(e.target.files[0]) //start conversion...
        reader.onload = function (e) { //.. once finished..
            let rawLog = reader.result.split(',')[1]; //extract only the file data part

            setLoadingApp("Loading...");

            let dataSend = {
                dataReq: {data: rawLog, name: file.name, type: file.type},
                fname: "uploadFilesToGoogleDrive"
            }; //preapre info to send to API
             fetch('https://script.google.com/macros/s/AKfycby3Ey1lmmyX9CAsRlanTAU4FveEyfKqnjrYQPTaaBHUEN6Z3OrF/exec', //your AppsScript URL
                {method: "POST", body: JSON.stringify(dataSend)}) //send to Api
                .then(res => res.json()).then((a) => {
                    changeApp(a.url);
                    setLoadingApp("");
                }).catch((e) => {
                    setLoadingApp("Something went wrong please try again later.\n" + e);
                })
        }
    }

    const {id} = useParams();

    //Variables
    const [icon, setIcon] = useState("");
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [arrayImages, setArrayImages] = useState([])
    const [app, setApp] = useState("");

    //Loading screens
    const [loadingImage, setLoadingImage] = useState("");
    const [loadingIcon, setLoadingIcon] = useState("");
    const [loadingApp, setLoadingApp] = useState("");
    const [loadingSavingApp, setLoadingSavingApp] = useState("");

    //Save changes
    function changeIcon(url) {

        let includeID = url.replace("file/d/", "uc?export=view?&id=");
        let ChangeView = includeID.replace("/view?usp=drivesdk", "");

        setIcon(ChangeView);
    }
    const changeName = (e) => {
        setName(e.target.value);
    };
    const changeDescription = (e) => {
        setDescription(e.target.value);
    }
    function AddImage(url) {
        let includeID = url.replace("file/d/", "uc?export=view?&id=");
        let ChangeView = includeID.replace("/view?usp=drivesdk", "");

        if (arrayImages === null) {
            setArrayImages(ChangeView);
        } else if (arrayImages.length < 10) {
            arrayImages.push(ChangeView);
            setArrayImages(arrayImages);
        }
        //ReactDOM.render( <div id="images"><LoadImages/></div>, document.getElementById('images'));
        setArrayImages(arrayImages);
    }
    const RemoveImage = (e) => {
        for (let i = 0; i < arrayImages.length; i++) {
            if (arrayImages[i] === e.target.value) {
                arrayImages.splice(i, 1);
                ReactDOM.render( <LoadImages/>, document.getElementById('images'));
                return;
            }
        }
    }
    function changeApp(url) {

        let includeID = url.replace("file/d/", "uc?export=view?&id=");
        let ChangeView = includeID.replace("/view?usp=drivesdk", "");

        setApp(ChangeView);
    }

    //Display images
    function LoadImages() {
        return (
            <div id="images" className={"screenshots-list"}>
                {arrayImages.map(image => (
                    <div className={"screenshot"}>
                        <img src={image} alt={"application screenshot"}/>
                        <button className={"delete-screenshot-button"} type="button" value={image} onClick={RemoveImage}>-</button>
                    </div>
                ))}
            </div>
        );
    }

    //Save app
    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const SendRequest = () =>{

        let data = {
            'name': name,
            'description': description,
            'screenshots': arrayImages,
            'icon':icon,
            'applicationLocation': app,
            'creatorID':id
        };

        axios.post(`http://localhost:8080/application`,data,config)
            .then(function (){})
            .catch(function (){});
    }
    const CheckInput = () =>{

        let result = "";

        if(icon == ""){
            result += "Please add a icon. \n";
        }
        if(description == ""){
            result +="Please add a description. \n";
        }
        if(name == "" ){
            result += "Please add a title. \n";
        }
        if(arrayImages == null || arrayImages.length == 0) {
            result += "Please add at least 1 image. \n";
        }
        if(app == "") {
            result += "Please make sure to include the url to the application. \n";
        }
        return result;
    }
    const SaveApp = () =>{
        let checkInput = CheckInput();

        if(checkInput != ""){
            alert(checkInput);}
        else{
            SendRequest();
            let path = `/creator/${id}/myApps`;
            navigate(path);
        }
    }


    return (
        <div className="container">
            <span id={"error"}/>
            <div className={"app-controls"}>
                <img className={"icon"} src={icon} alt={"application icon"}/>
                <h1>{name}</h1>
                <button className={"done-button"} onClick={SaveApp}>Done</button>
            </div>
            <hr/>
            <div className={"app-icon"}>
                <h2>Icon:</h2>
                <div className={"icon-controls"}>
                    <img src={icon} alt={"application icon"}/>
                    <label htmlFor="editIconButton"><FontAwesomeIcon className="edit-icon" icon={faPen} /></label>
                    <input id="editIconButton" type="file" accept="image/jpeg, image/png"
                           onChange={(e) => SaveArchiveIcon(e)}/>
                    <p className={"loading-icon"}>{loadingIcon}</p>
                </div>
            </div>
            <hr/>
            <div className={"app-name"}>
                <h2>Name:</h2>
                <input type="text" value={name} placeholder={"Type here..."} onChange={changeName}/>
            </div>
            <hr/>
            <div className="app-screenshots">
                <div className={"screenshots-header"}>
                    <h2>Screenshots:</h2>
                    <label htmlFor="addScreenshot"><FontAwesomeIcon className="add-screenshot-icon" icon={faCirclePlus} /></label>
                    <input id={"addScreenshot"} type="file" accept="image/jpeg, image/png" onChange={(e) => SaveArchiveImage(e)}/>
                    <p className={"loading-screenshot"}>{loadingImage}</p>
                </div>
                <LoadImages/>
            </div>
            <hr/>
            <div className={"app-description"}>
                <h2>Description:</h2>
                <textarea className={"description-field"} value={description} onChange={changeDescription}/>
            </div>
            <hr/>
            <div className={"app-file"}>
                <h2>File:</h2>
                <label htmlFor="addFile"><FontAwesomeIcon className="add-file-icon" icon={faCirclePlus} /></label>
                <input id={"addFile"} type="file" accept="application/zip" onChange={(e) => SaveArchiveApp(e)}/>
                <p className={"AppLoading"}> {loadingApp} </p>
            </div>
        </div>
    );
}

export default AddApp_creator;

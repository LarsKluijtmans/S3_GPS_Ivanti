import React, { useEffect, useState } from 'react'
import Application from './Application'
import axios from 'axios';
import {useParams} from "react-router-dom";
import { Section, List } from './ApplicationListStyled';


const ApplicationList = () => {
    
    const [applications, setApplications] = useState([]);
    
    const {id} = useParams();


    const getApplicationsByCreator = () => {
        axios.get(`http://localhost:8080/application/creator/${id}`)
        .then(response => {
            setApplications(response.data);
            console.log(response.data);
        })
        .catch(err => {
            console.log(err);
        })
    }

    useEffect(() => {
        getApplicationsByCreator();
    }, []);
    
    return (
        <Section>
            <List>
                {applications.length > 0 ? (
                    <>
                        {applications.map((app) => (
                            <Application key={app.name} name={app.name} icon={app.icon} />
                        ))}
                    </>
                    ) : (
                        <p>Loading applications</p>
                    )
                }
            </List>
        </Section>
    )
}

export default ApplicationList
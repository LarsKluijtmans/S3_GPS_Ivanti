import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Application from './Application'

const ApplicationList = () => {

    const [applications, setApplications] = useState([]);

    useEffect(() => {
        retrieveApps();
    }, []);

    const retrieveApps = () => {
         axios.get.getApplicationsByCreator()
        .then(response => {
            setApplications(response.data);
             console.log(response.data);
         })
         .catch(err => {
             console.log(err);
         })
    }

    return (
        <>
            {applications.length > 0 ? (
                <>
                    {applications.map((app) => (
                        <Application key={app.name} name={app.name} icon={app.icon} />
                    ))}
                </>
            ) : (
                <p>Loading apps...</p>
            )}
        </>
    )
}

export default ApplicationList
import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Application from '../../Creator/MyApps/Application';

const AllApplicationList = () => {

    const [applications, setApplications] = useState([]);

    const getApplications = () => {
        axios.get(`http://localhost:8080/application`)
        .then(response => {
            setApplications(response.data);
            console.log(response.data);
        })
        .catch (err => {
            console.log(err);
        })
    }
    
    useEffect(() => {
        getApplications()
    }, []);

    return (
        <section>
            <div>
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
            </div>
        </section>
  )
}

export default AllApplicationList
import React, { useEffect, useState } from 'react'
import Application from './components/Application'
import axios from 'axios';
import '../../../styles/ContentCreator/MyAppsPage/MyAppsPage.css';
import { useParams, Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faCaretDown, faCirclePlus, faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'
import ApplicationBasicCreator from './ApplicationBasicCreator';

const MyAppsPage = () => {
    const [applicationsArray, setApplicationsArray] = useState({});
    const [name, setName] = useState('');
    const [sort, setSort] = useState('');

    let urlParams = new URLSearchParams(window.location.search);

    const {username} = useParams();

    const getApplications = () => {
        axios.get(`http://localhost:8080/application/creator/${username}`)
            .then(res => {
                setApplicationsArray(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(() => {
        getApplications();
    }, []);

    function searchAndSort() {
        if (name && sort) {
            let newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?name=' + name + '&sort=' + sort;
            window.history.pushState({path: newUrl}, '', newUrl);
        } else if (name) {
            let newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?name=' + name;
            window.history.pushState({path: newUrl}, '', newUrl);
        } else if (sort) {
            let newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?sort=' + sort;
            window.history.pushState({path: newUrl}, '', newUrl);
        }

    }

    return (
        <div>

            <div className='my-apps'>
                <div className='my-apps-controls'>
                    <h1 className='title'>My Apps</h1>
                    <Link to='/creator/username/myApps/addApplication'><FontAwesomeIcon className='add-icon'
                                                                                        icon={faCirclePlus}/></Link>
                    <input className='search-field' type='text' placeholder='Search' vale={name}
                           onChange={(e) => setName(e.target.value)}/>
                    <div className='dropdown'>
                        <select value='sort' onChange={(e) => setSort(e.target.value)}>
                            <option value="nameAsc">Name Ascending</option>
                            <option value="nameDesc">Name Descending</option>
                            <option value="ratingAsc">Rating Ascending</option>
                            <option value="ratingDesc">Rating Descending</option>
                        </select>
                        <FontAwesomeIcon className="dropdown-icon" icon={faCaretDown}/>
                    </div>
                    <button className={"search-button"} type="button" onClick={searchAndSort}><FontAwesomeIcon
                        icon={faMagnifyingGlass}/></button>
                </div>
                <hr/>
                <div className='my-apps-list'>
                    {applicationsArray.myApplications && applicationsArray.myApplications.map((app) => (
                        <ApplicationBasicCreator key={app.name} name={app.name} icon={app.icon}/>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default MyAppsPage;

import axios from 'axios';
import React, { useEffect, useState } from 'react'
import AppsBasic from './AppsBasic';

const AppsPage = () => {

    const [applicationsArray, setApplicationArray] = useState([]);
    const [name, setName] = useState('');
    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };

    useEffect(() => {
        getApplications();
    }, []);

    const getApplications = () => {
        axios.get(`http://localhost:8080/application`)
        .then(res => {
            setApplicationArray(res.data);
            console.log(res.data);
        })
        .catch(err => {
            console.log(err);
        })
    }

  return (
    <>
        {applicationsArray.applications ? (
            <div className='my-apps'>
                <h1 className='title'>All Applications</h1>
                <input className='search-field' type="text" placeholder="Search" value={name} onChange={(e) => setName(e.target.value)} />
                {/* <div className={"dropdown"}>
                            <select value={sort} onChange={(e) => setSort(e.target.value)}>
                                <option value="nameAsc">Name Ascending</option>
                                <option value="nameDesc">Name Descending</option>
                                <option value="ratingAsc">Rating Ascending</option>
                                <option value="ratingDesc">Rating Descending</option>
                            </select>
                            <FontAwesomeIcon className="dropdown-icon" icon={faCaretDown} />
                            <button className={"search-button"} type="button" onClick={searchAndSort}><FontAwesomeIcon icon={faMagnifyingGlass}/></button>
                </div> */}
                <hr/>
                <div className='my-apps-list'>
                    { applicationsArray.applications && applicationsArray.applications.map((app) => (
                        <AppsBasic key={app.name} name={app.name} icon={app.icon} />
                    ))}
                </div>
            </div>
        ) : (
            <p>Loading applications...</p>
        )}
    </>
  )
}

export default AppsPage
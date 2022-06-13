import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faCaretDown, faChevronLeft, faChevronRight} from '@fortawesome/free-solid-svg-icons'
import { FaStar } from 'react-icons/fa'

const AppDetailed = () => {
    let params = useParams();

    const [application, setApplication] = useState({});
    const [version, setVersion] = useState("1.0");

    const getApplication = () => {
        axios.get(`http://localhost:8080/application/${params.name}`)
        .then(response => {
            setApplication(response.data);
            console.log(response.data);
        })
        .catch(err => {
            console.log(err);
        })
    }

    useEffect(() => {
        getApplication();
    }, []);

  return (
    <div className='app'>
            <div className={"app-controls"}>
                <img className='icon' alt='application logo' src={application.icon}/>
                <h1>{application.name}</h1>
                <Link className="delete-button" to={''}>Download</Link>
            </div>
            <hr/>
            <div className='app-version'>
                <h2>Version</h2>
                <div className={"version-content"}>
                    <dropdown className={"version-selector"}>
                        <select value={version} onChange={e=>setVersion(e.target.value)}>
                            { application.versions != null &&
                                application.versions.map((version) => (
                                    <option>{parseFloat(version.number).toFixed(1)}</option>
                                ))}
                        </select>
                        <FontAwesomeIcon className="dropdown-icon" icon={faCaretDown} />
                    </dropdown>
                </div>
            </div>
            <hr/>
            <div className='menu-wrapper'>
                <ul className='menu'>
                    { application.screenshots != null && application.screenshots.map((image) => (
                        <li className='item' key={image}><img src={image}/></li>
                    ))}
                </ul>
            </div>
            <hr/>
            <div className={"ratings-header"}>
                <h2>Ratings & Reviews</h2>
                <button className={"see-all-button"}>See All</button>
            </div>
            <div className={"overall-rating"}>
                <p className={"rating-number"}>{application.avgRating}</p>
                <p>out of 5</p>
            </div>
            <div className={"app-reviews"}>
                { application.reviews != null && application.reviews.map(review =>
                    <div className={"review-card"}>
                        <div className={"card-title"}>
                            <p className={"text"}>{review.title}</p>
                            <p className={"date"}>3y ago</p>
                        </div>
                        <div className={"card-stars"}>
                            <p className={"stars"}>
                                {[...Array(5)].map((star, i) => {
                                    const ratingValue = i + 1;
                                    return (
                                        <label>
                                            <FaStar
                                                className={"star"}
                                                color={ratingValue <= review.rating ? "#4F4746" : "#e4e5e9"}
                                                size={15}
                                            />
                                        </label>
                                    )
                                })}
                            </p>
                            <p className={"nickname"}>{review.customer}</p>
                        </div>
                        <p className={"card-description"}>{review.description}</p>
                    </div>
                )}
            </div>
            <hr/>
            <div className='app-description'>
                <h2>Description</h2>
                <p>{application.description}</p>
            </div>
        </div>
  )
}

export default AppDetailed
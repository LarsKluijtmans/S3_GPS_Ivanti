import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { FaStar } from 'react-icons/fa'
import '../../../styles/ContentCreator/ApplicationPage.css'
import axios from 'axios'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faCaretDown, faChevronLeft, faChevronRight} from '@fortawesome/free-solid-svg-icons'
/*import $ from 'jquery';*/

const ApplicationPage = () => {
    const params = useParams();

    const [application, setApplication] = useState({});
    const [version, setVersion] = useState("1.0");

    const getApplication = () => {
        axios.get(`http://localhost:8080/application/` + params.name)
        .then(response => {
            setApplication(response.data);
        })
        .catch(err => {
        })
    }

    const deleteApplication = () => {
      axios.delete(`http://localhost:8080/application/` + params.name)
          .then(response => {
          })
          .catch(err => {
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
                <Link className="category" to={''}>Utilities</Link>
                <Link className="edit-button" to={`/creator/1/myApps/${application.name}/updateApplication`}>Edit</Link>
                <Link className="delete-button" to={`/creator/1/myApps`} onClick={deleteApplication}>Delete</Link>
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
                    <Link className="version-button" to={`/creator/1/myApps/${application.name}/addMinorVersion`}>Add minor version</Link>
                    <Link className="version-button" to={`/creator/1/myApps/${application.name}/addMajorVersion`}>Add major version</Link>
                </div>
            </div>
            <hr/>
            <div className='menu-wrapper'>
                <ul className='menu'>
                    { application.screenshots != null && application.screenshots.map((image) => (
                        <li className='item' key={image}><img src={image}/></li>
                    ))}
                </ul>
                {/* ARROWS
                    <div className="paddles">
                        <button className="left-paddle paddle hidden">
                            <FontAwesomeIcon className={"paddle-icon"} icon={faChevronLeft}/>
                        </button>
                        <button className="right-paddle paddle">
                            <FontAwesomeIcon className={"paddle-icon"} icon={faChevronRight}/>
                        </button>
                    </div>
                */}
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
                        <button className={"reply-button"}>Reply</button>
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

export default ApplicationPage
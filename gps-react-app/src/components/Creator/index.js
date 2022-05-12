import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom';

// icons
import { AiOutlinePlusCircle, AiOutlineSearch, AiFillCaretDown } from 'react-icons/ai'
import MyApps from './MyApps';

const Creator = () => { 

    const [creator, setCreator] = useState({});

    const {id} = useParams();

    const getCreator = () => {
        axios.get(`http://localhost:8080/api/user/creator/${id}`)
        .then(response => {
            setCreator(response.data);
            console.log(response.data);
        })
        .catch(err => {
            console.log(err);
        })
    }

    useEffect(() => {
        getCreator();
    }, []);


    const fakeCreator = () => {
        setCreator({
            firstName: "firstName",
            lastName: "lastName"
        })
    }

  return (
    <>
        {Object.keys(creator).length !== 0 ? (
            <>
                <h1>Hello, {creator.firstName} {creator.lastName}</h1>
                <div className='my-apps'>
                    <div className='title'>
                        <h1>My Apps:</h1>
                    </div>
                    <div className='title-buttons'>
                        <div className='add-button'>
                            <Link to={`/Creator/AddApp/${id}`}>
                                Add App <AiOutlinePlusCircle />
                            </Link>
                        </div>
                        {/* <AiOutlineSearch />
                        <p>Sort <AiFillCaretDown /></p> */}
                    </div>
                </div>
                <MyApps />
            </>
        ) : (
            <p>Access denied</p>
        )}
    </>
  )
}

export default Creator


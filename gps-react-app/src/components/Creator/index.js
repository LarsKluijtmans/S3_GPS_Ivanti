import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom';

// icons
import { AiOutlinePlusCircle, AiOutlineSearch, AiFillCaretDown } from 'react-icons/ai'
import MyApps from './MyApps';

const Creator = () => { 

    // get creator by id
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
        // fakeCreator();
    }, []);

    // load apps for creator by id

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
                    <h1>My Apps</h1>
                    <Link to='/'>
                        <AiOutlinePlusCircle color='red'/>
                    </Link>
                    <AiOutlineSearch />
                    <div>
                        <p>Sort <AiFillCaretDown /></p>
                    </div>
                    <MyApps />
                </div>
            </>
        ) : (
            <p>Access denied</p>
        )}
    </>
  )
}

export default Creator

// {applications.length > 0 ? (
//     <>
//         {applications.map((app) => (
//             <Application key={app.name} name={app.name} icon={app.icon} />
//         ))}
//     </>
//     ) : (
//         <p>Loading applications</p>
//     )
// }
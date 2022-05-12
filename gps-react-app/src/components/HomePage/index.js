import React from 'react'
import AllApplicationList from './Apps/AllApplicationList'

const HomePage = () => {



  return (
    <>
        {/* {Object.keys(creator).length !== 0 ? (
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
                    {/* </div>
                </div>
                <MyApps />
            </>
        ) : (
            <p>Access denied</p>
        )} */} 
        <div className='my-apps'>
            <div className='title'>
                <h1>Featured Apps:</h1>
            </div>
        </div>
        <AllApplicationList />
    </>
  )
}

export default HomePage
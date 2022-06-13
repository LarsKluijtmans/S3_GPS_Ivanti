import React from 'react'
import { Link } from 'react-router-dom'

const AppsBasic = ({ name, icon }) => {
  return (
    <Link className='application' to={`/app/${name}`}>
        <img src={icon} />
        <p>{name}</p>
    </Link>
  )
}

export default AppsBasic
import React from 'react'
import { Link } from 'react-router-dom'

const AppsBasic_creator = ({ name, icon }) => {
  return (
    <Link className='application' to={`/creator/myApps/${name}`}>
        <img src={icon} />
        <p>{name}</p>
    </Link>
  )
}

export default AppsBasic_creator

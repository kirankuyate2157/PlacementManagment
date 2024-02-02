import { useState } from "react";

import { viteLogo } from '../../public/vite.svg';

export default function SignUp() {
  const [userData, setUserData] = useState({
    email: "",
    password: "",
  });
  
  const handleChange = (e) =>
    setUserData((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const Submit = (e) => {
    e.preventDefault();

    if (!userData.email) {
      alert("Email is required !");
    }
    if (!userData.password) {
      alert("Password is required !");
    }

}


  return (
    <>
      <div className='flex flex-row w-full p-20  justify-center  align-middle'>
        <div className='inline-block w-full max-w-md md:p-6 md:my-8 overflow-hidden text-left align-middle transition-all transform  shadow-sm rounded-2xl'>
          <div className=' flex flex-col w-full'>
            <div className='  items-center  flex flex-col gap-3 '>
              <div>
                <img src={viteLogo} alt='nari logo' className='h-48 ' />
              </div>
            </div>

            <form className='flex flex-col gap-3'>
              <div className=' w-full flex flex-col gap-2'>
                <input
                  type='text'
                  id='email'
                  name='email'
                  value={userData.email}
                  onChange={handleChange}
                  placeholder='email..'
                  className='w-full border border-bcc-500 px-3 py-2 rounded-xl focus:outline-none focus:border-zomato-500'
                />
              </div>
              <div className=' w-full flex flex-col gap-2'>
                <input
                  type='password'
                  id='password'
                  placeholder='password..'
                  value={userData.password}
                  onChange={handleChange}
                  name='password'
                  className='w-full border border-bcc-500 px-3 py-2 rounded-xl focus:outline-none focus:border-zomato-500'
                />
              </div>
              <div
                onClick={(e) => {
                  Submit(e);
                }}
                className='w-full  text-center bg-bcc-500 text-white border border-gray-500  hover:bg-bcc-700 py-2 rounded-xl'
              >
                Sign In
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}

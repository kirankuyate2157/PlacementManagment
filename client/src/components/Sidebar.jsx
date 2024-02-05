import { FaBuildingColumns } from "react-icons/fa6";

const Sidebar = () => {
  return (
    <div className="" >
      <aside
        id='sidebar'
        className=' z-40 h-screen w-0 sm:w-48  sm:block transition-transform'
        aria-label='Sidebar'
      >
        <div className=' h-full flex-col hidden sm:flex overflow-y-auto border-r border-slate-200 bg-white px-3 py-4 dark:border-slate-700 dark:bg-slate-900'>
          <div
            href='#'
            className='mb-10 flex items-center rounded px-3 py-2 text-slate-900 dark:text-white'
          >
            <svg
              className='h-5 w-5'
              aria-hidden='true'
              xmlns='http://www.w3.org/2000/svg'
              width='24'
              height='24'
              viewBox='0 0 24 24'
              fill='none'
              stroke='currentColor'
              stroke-width='2'
              stroke-linecap='round'
              stroke-linejoin='round'
              className='lucide lucide-command'
            >
              <path d='M15 6v12a3 3 0 1 0 3-3H6a3 3 0 1 0 3 3V6a3 3 0 1 0-3 3h12a3 3 0 1 0-3-3' />
            </svg>
            <span className='ml-3 text-base font-semibold'>Kways</span>
          </div>
          <ul className='space-y-2 text-sm font-medium'>
            <li>
              <a
                href='#'
                className='flex items-center rounded px-3 py-2 text-slate-900 hover:bg-slate-100 dark:text-white dark:hover:bg-slate-700'
              >
                <svg
                  xmlns='http://www.w3.org/2000/svg'
                  className='h-5 w-5'
                  width='24'
                  height='24'
                  aria-hidden='true'
                  viewBox='0 0 24 24'
                  fill='none'
                  stroke='currentColor'
                  stroke-width='2'
                  stroke-linecap='round'
                  stroke-linejoin='round'
                  className='lucide lucide-home'
                >
                  <path d='m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z' />
                  <polyline points='9 22 9 12 15 12 15 22' />
                </svg>
                <span className='ml-3 flex-1 whitespace-nowrap'>Placement</span>
              </a>
            </li>
            <li>
              <a
                href='#'
                className='flex items-center rounded px-3 py-2 text-slate-900 hover:bg-slate-100 dark:text-white dark:hover:bg-slate-700'
              >
                <svg
                  xmlns='http://www.w3.org/2000/svg'
                  className='h-5 w-5'
                  width='24'
                  height='24'
                  aria-hidden='true'
                  viewBox='0 0 24 24'
                  fill='none'
                  stroke='currentColor'
                  stroke-width='2'
                  stroke-linecap='round'
                  stroke-linejoin='round'
                  className='lucide lucide-users'
                >
                  <path d='M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2' />
                  <circle cx='9' cy='7' r='4' />
                  <path d='M22 21v-2a4 4 0 0 0-3-3.87' />
                  <path d='M16 3.13a4 4 0 0 1 0 7.75' />
                </svg>
                <span className='ml-3 flex-1 whitespace-nowrap'>Student</span>
              </a>
            </li>
            <li>
              <a
                href='#'
                className='flex items-center rounded px-3 py-2 text-slate-900 hover:bg-slate-100 dark:text-white dark:hover:bg-slate-700'
              >
               <FaBuildingColumns className="text-xl"/>
                <span className='ml-3 flex-1 whitespace-nowrap'>College</span>
              </a>
            </li>
            <li>
              <a
                href='#'
                className='flex items-center rounded px-3 py-2 text-slate-900 hover:bg-slate-100 dark:text-white dark:hover:bg-slate-700'
              >
                <svg
                  xmlns='http://www.w3.org/2000/svg'
                  className='h-5 w-5'
                  width='24'
                  height='24'
                  aria-hidden='true'
                  viewBox='0 0 24 24'
                  fill='none'
                  stroke='currentColor'
                  stroke-width='2'
                  stroke-linecap='round'
                  stroke-linejoin='round'
                  className='lucide lucide-settings'
                >
                  <path d='M12.22 2h-.44a2 2 0 0 0-2 2v.18a2 2 0 0 1-1 1.73l-.43.25a2 2 0 0 1-2 0l-.15-.08a2 2 0 0 0-2.73.73l-.22.38a2 2 0 0 0 .73 2.73l.15.1a2 2 0 0 1 1 1.72v.51a2 2 0 0 1-1 1.74l-.15.09a2 2 0 0 0-.73 2.73l.22.38a2 2 0 0 0 2.73.73l.15-.08a2 2 0 0 1 2 0l.43.25a2 2 0 0 1 1 1.73V20a2 2 0 0 0 2 2h.44a2 2 0 0 0 2-2v-.18a2 2 0 0 1 1-1.73l.43-.25a2 2 0 0 1 2 0l.15.08a2 2 0 0 0 2.73-.73l.22-.39a2 2 0 0 0-.73-2.73l-.15-.08a2 2 0 0 1-1-1.74v-.5a2 2 0 0 1 1-1.74l.15-.09a2 2 0 0 0 .73-2.73l-.22-.38a2 2 0 0 0-2.73-.73l-.15.08a2 2 0 0 1-2 0l-.43-.25a2 2 0 0 1-1-1.73V4a2 2 0 0 0-2-2z' />
                  <circle cx='12' cy='12' r='3' />
                </svg>
                <span className='ml-3 flex-1 whitespace-nowrap'>Settings</span>
              </a>
            </li>
          </ul>
          <div className='mt-auto flex'>
            <div className='flex w-full justify-between'>
              <span className='text-sm font-medium text-black dark:text-white'>
                email@example.com
              </span>
              <svg
                xmlns='http://www.w3.org/2000/svg'
                width='24'
                height='24'
                viewBox='0 0 24 24'
                aria-roledescription='more menu'
                fill='none'
                stroke='currentColor'
                stroke-width='2'
                className='h-5 w-5 text-black dark:text-white'
                stroke-linecap='round'
                stroke-linejoin='round'
                className='lucide lucide-more-horizontal'
              >
                <circle cx='12' cy='12' r='1' />
                <circle cx='19' cy='12' r='1' />
                <circle cx='5' cy='12' r='1' />
              </svg>
            </div>
          </div>
        </div>
      </aside>

    </div>
  );
};

export default Sidebar;

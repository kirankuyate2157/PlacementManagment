const orders = {
  status: "success",
  totalSales: {
    name: "Total Sales & Cost (Delivered)",
    totalCost: {
      firstWeek: 0,
      secondWeek: 0,
    },
    sales: [
      {
        date: "2024-03-30",
        dayName: "Saturday",
        totalSales: 0,
      },
      {
        date: "2024-03-31",
        dayName: "Sunday",
        totalSales: 0,
      },
      {
        date: "2024-04-01",
        dayName: "Monday",
        totalSales: 0,
      },
      {
        date: "2024-04-02",
        dayName: "Tuesday",
        totalSales: 0,
      },
      {
        date: "2024-04-03",
        dayName: "Wednesday",
        totalSales: 0,
      },
      {
        date: "2024-04-04",
        dayName: "Thursday",
        totalSales: 0,
      },
      {
        date: "2024-04-05",
        dayName: "Friday",
        totalSales: 0,
      },
      {
        date: "2024-04-06",
        dayName: "Saturday",
        totalSales: 0,
      },
      {
        date: "2024-04-07",
        dayName: "Sunday",
        totalSales: 0,
      },
      {
        date: "2024-04-08",
        dayName: "Monday",
        totalSales: 0,
      },
      {
        date: "2024-04-09",
        dayName: "Tuesday",
        totalSales: 0,
      },
      {
        date: "2024-04-10",
        dayName: "Wednesday",
        totalSales: 0,
      },
      {
        date: "2024-04-11",
        dayName: "Thursday",
        totalSales: 0,
      },
      {
        date: "2024-04-12",
        dayName: "Friday",
        totalSales: 0,
      },
    ],
  },
  pendingOrders: {
    name: "Pending Orders",
    totalCost: {
      firstWeek: 0,
      secondWeek: 3299,
    },
    sales: [
      {
        date: "2024-04-06",
        dayName: "Saturday",
        totalSales: 0,
      },
      {
        date: "2024-04-07",
        dayName: "Sunday",
        totalSales: 1,
      },
      {
        date: "2024-04-08",
        dayName: "Monday",
        totalSales: 0,
      },
      {
        date: "2024-04-09",
        dayName: "Tuesday",
        totalSales: 0,
      },
      {
        date: "2024-04-10",
        dayName: "Wednesday",
        totalSales: 0,
      },
      {
        date: "2024-04-11",
        dayName: "Thursday",
        totalSales: 0,
      },
      {
        date: "2024-04-12",
        dayName: "Friday",
        totalSales: 0,
      },
    ],
  },
  allOrders: {
    name: "Total Orders",
    totalCost: {
      firstWeek: 0,
      secondWeek: 3299,
    },
    sales: [
      {
        date: "2024-04-06",
        dayName: "Saturday",
        totalSales: 0,
      },
      {
        date: "2024-04-07",
        dayName: "Sunday",
        totalSales: 1,
      },
      {
        date: "2024-04-08",
        dayName: "Monday",
        totalSales: 0,
      },
      {
        date: "2024-04-09",
        dayName: "Tuesday",
        totalSales: 0,
      },
      {
        date: "2024-04-10",
        dayName: "Wednesday",
        totalSales: 0,
      },
      {
        date: "2024-04-11",
        dayName: "Thursday",
        totalSales: 0,
      },
      {
        date: "2024-04-12",
        dayName: "Friday",
        totalSales: 0,
      },
    ],
  },
  returnOrders: {
    name: "Return Orders",
    totalCost: {
      firstWeek: 0,
      secondWeek: 0,
    },
    sales: [
      {
        date: "2024-04-06",
        dayName: "Saturday",
        totalSales: 0,
      },
      {
        date: "2024-04-07",
        dayName: "Sunday",
        totalSales: 0,
      },
      {
        date: "2024-04-08",
        dayName: "Monday",
        totalSales: 0,
      },
      {
        date: "2024-04-09",
        dayName: "Tuesday",
        totalSales: 0,
      },
      {
        date: "2024-04-10",
        dayName: "Wednesday",
        totalSales: 0,
      },
      {
        date: "2024-04-11",
        dayName: "Thursday",
        totalSales: 0,
      },
      {
        date: "2024-04-12",
        dayName: "Friday",
        totalSales: 0,
      },
    ],
  },
  customersData: [
    {
      count: 1,
      date: "2024-02-07",
      customers: ["7675300479283"],
    },
    {
      count: 1,
      date: "2024-02-09",
      customers: ["7675300479283"],
    },
    {
      count: 1,
      date: "2024-02-17",
      customers: ["7675300479283"],
    },
    {
      count: 3,
      date: "2024-02-21",
      customers: ["7675300479283", "7714623521075", "7639453139251"],
    },
    {
      count: 1,
      date: "2024-02-22",
      customers: ["7675300479283"],
    },
    {
      count: 3,
      date: "2024-02-23",
      customers: ["7639453139251", "7675300479283"],
    },
    {
      count: 3,
      date: "2024-02-24",
      customers: ["7675300479283", "7639453139251"],
    },
    {
      count: 5,
      date: "2024-02-27",
      customers: ["7639453139251", "7714623521075", "7675300479283"],
    },
    {
      count: 16,
      date: "2024-03-01",
      customers: ["7714623521075"],
    },
    {
      count: 1,
      date: "2024-03-07",
      customers: ["7714623521075"],
    },
    {
      count: 1,
      date: "2024-03-09",
      customers: ["7763376800051"],
    },
    {
      count: 1,
      date: "2024-03-14",
      customers: ["7714623521075"],
    },
    {
      count: 2,
      date: "2024-03-16",
      customers: ["7675300479283", "7714623521075"],
    },
    {
      count: 5,
      date: "2024-03-20",
      customers: [
        "7714623521075",
        "7794108530995",
        "7794808652083",
        "7789143163187",
      ],
    },
    {
      count: 1,
      date: "2024-03-23",
      customers: ["7714623521075"],
    },
    {
      count: 1,
      date: "2024-03-24",
      customers: ["7714623521075"],
    },
    {
      count: 1,
      date: "2024-03-31",
      customers: ["7810259616051"],
    },
    {
      count: 1,
      date: "2024-04-07",
      customers: ["7806839554355"],
    },
  ],
  productAnalytics: [
    {
      ids: [
        "65bbbd73877b6bb6d6749362",
        "65bbc090877b6bb6d6749384",
        "65c092f27ce23d5c40181805",
      ],
      count: 3,
      date: null,
    },
    {
      ids: ["65aa3f5ba9f47de028e3a1d2"],
      count: 1,
      date: "2024-01-19",
    },
    {
      ids: ["65e1de0fb42fcfa70598e981"],
      count: 1,
      date: "2024-03-01",
    },
    {
      ids: ["65ec4d0f1bb4c726d49144b5"],
      count: 1,
      date: "2024-03-09",
    },
    {
      ids: ["65f69ab6e0b669ad1928afef"],
      count: 1,
      date: "2024-03-17",
    },
    {
      ids: [
        "65f831810a5a057285c00b87",
        "65f8323a0a5a057285c00bb0",
        "65f833df0a5a057285c00c73",
        "65f834ee0a5a057285c00ca2",
      ],
      count: 4,
      date: "2024-03-18",
    },
    {
      ids: ["65f973fd6544bdab02eff6f9"],
      count: 1,
      date: "2024-03-19",
    },
    {
      ids: ["660f93f2c08dc749fe6cc258"],
      count: 1,
      date: "2024-04-05",
    },
    {
      ids: ["661136dfcf23370af1dea310"],
      count: 1,
      date: "2024-04-06",
    },
    {
      ids: ["661408a78c6742fca9dead03"],
      count: 1,
      date: "2024-04-08",
    },
    {
      ids: ["66153997c7c5ef2887d47a09"],
      count: 1,
      date: "2024-04-09",
    },
    {
      ids: ["66165f4d4153f0001422a087", "6616aa49d9b3c64fd23e4831"],
      count: 2,
      date: "2024-04-10",
    },
  ],
  productStockData: [
    {
      variantCount: 6,
      name: "Apple Macbook Pro",
      count: 145,
      date: "2024-01-19",
    },
    {
      variantCount: 3,
      name: "Noise-Canceling Headphones",
      count: 348,
      date: "2024-02-01",
    },
    {
      variantCount: 3,
      name: "Smart Home Security Camera",
      count: 583,
      date: "2024-02-01",
    },
    {
      variantCount: 3,
      name: "cricket bats",
      count: 1186,
      date: "2024-02-05",
    },
    {
      variantCount: 4,
      name: "Test Product ",
      count: 1300,
      date: "2024-03-01",
    },
    {
      variantCount: 2,
      name: "Shoes",
      count: 19,
      date: "2024-03-09",
    },
    {
      variantCount: 2,
      name: "WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive",
      count: 10010,
      date: "2024-03-17",
    },
    {
      variantCount: 2,
      name: "Mens Cotton Jacket",
      count: 1999,
      date: "2024-03-18",
    },
    {
      variantCount: 2,
      name: "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultrawide Screen QLED",
      count: 11000,
      date: "2024-03-18",
    },
    {
      variantCount: 2,
      name: "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
      count: 110000,
      date: "2024-03-18",
    },
    {
      variantCount: 2,
      name: "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s",
      count: 110000,
      date: "2024-03-18",
    },
    {
      variantCount: 2,
      name: "Rain Jacket Women Windbreaker Striped Climbing Raincoats",
      count: 109995,
      date: "2024-03-19",
    },
    {
      variantCount: 3,
      name: "AI model",
      count: 36,
      date: "2024-04-05",
    },
    {
      variantCount: 1,
      name: "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket",
      count: 100000,
      date: "2024-04-06",
    },
    {
      variantCount: 2,
      name: "basket ball",
      count: 32,
      date: "2024-04-08",
    },
    {
      variantCount: 2,
      name: "green velly",
      count: 24,
      date: "2024-04-09",
    },
    {
      variantCount: 5,
      name: "Floor Mats",
      count: 3470,
      date: "2024-04-10",
    },
    {
      variantCount: 1,
      name: "MBJ Women's Solid Short Sleeve Boat Neck V",
      count: 10000,
      date: "2024-04-10",
    },
  ],
  productOutOfStockData: [],
  revenue: [
    {
      date: "2024-02-21",
      count: 2499,
    },
    {
      date: "2024-02-24",
      count: 67778,
    },
    {
      date: "2024-02-27",
      count: 135556,
    },
    {
      date: "2024-03-01",
      count: 216728,
    },
    {
      date: "2024-03-14",
      count: 2499,
    },
    {
      date: "2024-03-16",
      count: 3898,
    },
    {
      date: "2024-03-20",
      count: 5099,
    },
    {
      date: "2024-03-31",
      count: 3300,
    },
    {
      date: "2024-04-07",
      count: 2499,
    },
  ],
  topCategories: [
    {
      count: 5,
      name: "Electronics",
    },
    {
      count: 4,
      name: "Men",
    },
    {
      count: 3,
      name: "Women",
    },
    {
      count: 1,
      name: "Sport",
    },
    {
      count: 1,
      name: "Child",
    },
  ],
};

export { orders };

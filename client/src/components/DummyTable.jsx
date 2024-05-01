import React from 'react';

// Dummy data array
const dummyData = [
  { id: 1, issueDate: '2024-04-15', totalPrice: '$50.00', action: 'View' },
  { id: 2, issueDate: '2024-04-16', totalPrice: '$30.25', action: 'View' },
  { id: 3, issueDate: '2024-04-16', totalPrice: '$120.00', action: 'Edit' },
  { id: 4, issueDate: '2024-04-17', totalPrice: '$75.50', action: 'Edit' },
  { id: 5, issueDate: '2024-04-17', totalPrice: '$90.20', action: 'Delete' },
];

const DummyTable = () => {
  return (
    <div className="w-full h-full">
      <table className="w-full h-full">
        <thead>
          <tr>
            <th>ID</th>
            <th>Issue Date</th>
            <th>Total Price</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {dummyData.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.issueDate}</td>
              <td>{item.totalPrice}</td>
              <td>{item.action}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DummyTable;

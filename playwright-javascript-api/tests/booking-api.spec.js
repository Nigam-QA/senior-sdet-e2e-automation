const { test, expect } = require('@playwright/test');

test.describe('Restful Booker API', () => {
  test('GET booking IDs returns a successful response', async ({ request }) => {
    const response = await request.get('/booking');
    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(Array.isArray(body)).toBeTruthy();
    if (body.length) expect(body[0]).toHaveProperty('bookingid');
  });

  test('create, retrieve, update and delete a booking', async ({ request }) => {
    const authResponse = await request.post('/auth', {
      data: { username: 'admin', password: 'password123' }
    });
    expect(authResponse.ok()).toBeTruthy();
    const { token } = await authResponse.json();
    expect(token).toBeTruthy();

    const bookingData = {
      firstname: 'Nigam',
      lastname: 'SDET',
      totalprice: 250,
      depositpaid: true,
      bookingdates: { checkin: '2027-01-10', checkout: '2027-01-14' },
      additionalneeds: 'Breakfast'
    };

    const createResponse = await request.post('/booking', { data: bookingData });
    expect(createResponse.status()).toBe(200);
    const created = await createResponse.json();
    expect(created.bookingid).toBeTruthy();

    const id = created.bookingid;
    const getResponse = await request.get(`/booking/${id}`);
    expect(getResponse.ok()).toBeTruthy();
    expect((await getResponse.json()).firstname).toBe('Nigam');

    const updatedData = { ...bookingData, totalprice: 300 };
    const updateResponse = await request.put(`/booking/${id}`, {
      data: updatedData,
      headers: { Cookie: `token=${token}` }
    });
    expect(updateResponse.ok()).toBeTruthy();
    expect((await updateResponse.json()).totalprice).toBe(300);

    const deleteResponse = await request.delete(`/booking/${id}`, {
      headers: { Cookie: `token=${token}` }
    });
    expect([200, 201, 204]).toContain(deleteResponse.status());
  });

  test('unknown booking returns not found', async ({ request }) => {
    const response = await request.get('/booking/999999999');
    expect(response.status()).toBe(404);
  });
});

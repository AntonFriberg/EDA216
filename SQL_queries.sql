-- Search pallet by date.
SELECT *
FROM Pallet
WHERE bakeDate LIKE '?';

-- Search pallet by NBR_FIELDS
SELECT *
FROM Pallet
WHERE palletNbr LIKE '?'

-- Search all
set @search := '?';
SELECT *
FROM Pallet WHERE palletNbr like @search
              OR cookieName like @search
              OR bakeDate like @search
              OR isBlocked like @search
              OR location like @search
              OR billId like @search;

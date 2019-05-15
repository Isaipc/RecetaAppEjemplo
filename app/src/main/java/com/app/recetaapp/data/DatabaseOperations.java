package com.app.recetaapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.recetaapp.data.RecetaContract.RecetaEntry;
import com.app.recetaapp.POJO.Receta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseOperations
{

    private static DbHelper database;
    private static DatabaseOperations instance = new DatabaseOperations();

    public DatabaseOperations() {
    }

    public static DatabaseOperations getInstance(Context context) {
        if ( database == null)
            database = new DbHelper(context);

        return instance;
    }

    public SQLiteDatabase getDatabase() { return database.getWritableDatabase(); }


    /// Operaciones Receta

    public boolean insertReceta(Receta receta)
    {
        SQLiteDatabase db = database.getWritableDatabase();

        return db.insertOrThrow( RecetaContract.RecetaEntry.TABLE_NAME,
                null,
                receta.toContentValues()) > 0;
    }

    public void insertRecetas(List<Receta> recetaList)
    {
        for (Receta receta: recetaList) {
            insertReceta( receta );
        }


    }

    public long getItemsCounts()
    {
        return DatabaseUtils.queryNumEntries(
                database.getReadableDatabase(),
                RecetaEntry.TABLE_NAME );
    }

    public void createSampleData()
    {
        List <Receta> recetaList = new ArrayList<Receta>(  );

        if(getItemsCounts() > 0)
            return;

        recetaList.add(
                new Receta(
                        "Crock Pot Roast",
                        (int)(Math.random()*10)+1,
                        "1  beef roast Meat1 package brown gravy mix Baking1 package dried Italian salad dressing mix Condiments1 package dry ranch dressing mix Condiments1/2 cup water Drinks",
                        "Place beef roast in crock pot. Mix the dried mixes together in a bowl and sprinkle over the roast. Pour the water around the roast. Cook on low for 7-9 hours. ",
                        "http://img.sndimg.com/food/image/upload/w_266/v1/img/recipes/27/20/8/picVfzLZo.jpg",
                        1)
        );

        recetaList.add(
                new Receta(
                        "Curried Lentils and Rice",
                        (int)(Math.random()*10)+ 1,
                        "1 quart beef broth Misc1 cup dried green lentils Misc1/2 cup basmati rice Misc1 tsp curry powder Condiments1 tsp salt Condiments",
                        "Bring broth to a low boil. Add curry powder and salt. Cook lentils for 20 minutes. Add rice and simmer for 20 minutes. Enjoy! ",
                        "http://dagzhsfg97k4.cloudfront.net/wp-content/uploads/2012/05/lentils3.jpg",
                        0)
        );

        recetaList.add(
                new Receta(
                        "Cranberry and Apple Stuffed Acorn Squash Recipe",
                        (int)(Math.random()*10) + 1,
                        "2 acorn squash Produce1 boiling water Drinks2 apples chopped into 1.4 inch pieces Produce1/2 cup dried cranberries Produce1 teaspoon cinnamon Baking2 tablespoons melted butter Dairy",
                        "Cut squash in half  remove seeds. Place squash in baking dish  cut-side down. Pour 1/4-inch water into dish. Bake for 30 minutes at 350 degrees F. In large bowl  combine remaining ingredients. Remove squash from oven  fill with mix. Bake for 30-40 more minutes  until squash tender. Enjoy! ",
                        "http://elanaspantry.com/wp-content/uploads/2008/10/acorn_squash_with_cranberry.jpg",
                        0)
        );

        recetaList.add(
                new Receta(
                        "Roasted Asparagus",
                        (int)(Math.random()*10) + 1,
                        "1 lb  asparagus Produce1 1/2 tbsp olive oil Condiments1/2 tsp kosher salt Baking",
                        "Preheat oven to 425���F. Cut off the woody bottom part of the asparagus spears and discard. With a vegetable peeler  peel off the skin on the bottom 2-3 inches of the spears (this keeps the asparagus from being all.\" string.\"  and if you eat asparagus you know what I mean by that). Place asparagus on foil-lined baking sheet and drizzle with olive oil. Sprinkle with salt. With your hands  roll the asparagus around until they are evenly coated with oil and salt. Roast for 10-15 minutes  depending on the thickness of your stalks and how tender you like them. They should be tender when pierced with the tip of a knife. The tips of the spears will get very brown but watch them to prevent burning. They are great plain  but sometimes I serve them with a light vinaigrette if we need something acidic to balance out our meal. ",
                        "http://img.sndimg.com/food/image/upload/w_266/v1/img/recipes/50/84/7/picMcSyVd.jpg",
                        1)
        );

        recetaList.add(
                new Receta(
                        "Big Night Pizza",
                        (int)(Math.random()*10) + 1,
                        "5 teaspoons yeast Baking5 cups flour Baking4 tablespoons vegetable oil Baking2 tablespoons sugar Baking2 teaspoons salt Baking2 cups hot water Misc1/4 cup pizza sauce Misc3/4 cup mozzarella cheese Dairy",
                        "Add hot water to yeast in a large bowl and let sit for 15 minutes. Mix in oil  sugar  salt  and flour and let sit for 1 hour. Knead the dough and spread onto a pan. Spread pizza sauce and sprinkle cheese. Add any optional toppings as you wish. Bake at 400 deg Fahrenheit for 15 minutes. Enjoy! ",
                        "http://upload.wikimedia.org/wikipedia/commons/c/c7/Spinach_pizza.jpg",
                        0)
        );

        recetaList.add(
                new Receta(
                        "Blueberry Oatmeal Squares",
                        (int)(Math.random()*10) + 1,
                        "2-1/2 cups rolled oats  (not instant) Baking1-1/4 cups all-purpose flour Baking1 tbsp grated orange rind Produce1/4 tsp salt Baking1 cup cold butter  cubed Baking3/4 cup packed brown sugar Baking3 cups fresh blueberries Produce1/2 cup granulated sugar Baking1/3 cup orange juice Produce4 tsp cornstarch Baking",
                        "Filling: In saucepan  bring blueberries  sugar and orange juice to boil; reduce heat and simmer until tender  about 10 minutes. Whisk cornstarch with 2 tbsp (25 mL) water; whisk into blueberries and boil  stirring  until thickened  about 1 minute. Place plastic wrap directly on surface; refrigerate until cooled  about 1 hour. In large bowl  whisk together oats  flour  sugar  orange rind and salt ; with pastry blender  cut in butter until in coarse crumbs. Press half into 8-inch (2 L) square parchment paper���lined metal cake pan; spread with blueberry filling. Bake in centre of 350���F oven until light golden  about 45 minutes. Let cool on rack before cutting into squares. (Make-ahead: Cover and refrigerate for up to 2 days or overwrap with heavy-duty foil and freeze for up to 2 weeks.) ",
                        "http://www.canadianliving.com/img/photos/biz/blueberry-oatmeal-squares5801359401371.jpg",
                        1)
        );

        recetaList.add(
                new Receta(
                        "Curried chicken salad",
                        (int)(Math.random()*10) + 1,
                        "3 skinless  boneless chicken breasts  halved lengthwise Meat1/2 cup mayonnaise Baking1 tbsp lemon zest Produce1 tbsp  lemon juice Produce1 1/2 tsp curry powder Baking1/4 tsp salt Baking2 ripe mangoes  diced Produce1/4 cup dried cranberries Produce2 green onions  thinly sliced Produce1 celery stalk  finely chopped Produce6 leaves Boston lettuce Produce6 English muffins  toasted Misc",
                        "ARRANGE chicken in a single layer in a large pot. Add water to just cover. Bring to a boil over medium-high. Flip chicken  reduce heat to medium and simmer until cooked  about 6 more min. Cool. STIR mayo with lemon zest  juice  curry and salt in large bowl. Using 2 forks  shred chicken  then stir into mayo mixture with mango  cranberries  green onions and celery. Divide among muffins with lettuce leaves Sandwich with tops ",
                        "http://www.chatelaine.com/wp-content/uploads/2013/05/Curried-chicken-salad.jpg",
                        1)
        );

        recetaList.add(
                new Receta(
                        "Old-Fashioned Oatmeal Cookies",
                        (int)(Math.random()*10) + 1,
                        "1 cup raisins Produce1 cup water Drinks3/4 cup shortening Baking1 1/2 cups sugar Baking2 1/2 cups flour Baking1 tsp. soda Baking1 tsp. salt Baking1 tsp. cinnamon Baking1/2 tsp. baking powder Baking1/2 tsp. cloves Baking2 cups oats Baking1/2 cup chopped nuts Baking",
                        "Simmer raisins and water over medium heat until raisins are plump  about 15 minutes. Drain raisins  reserving the liquid. Add enough water to reserved liquid to measure 1/2 cup. Heat oven to 400���. Mix thoroughly shortening  sugar  eggs and vanilla. Stir in reserved liquid. Blend in remaining ingredients. Drop dough by rounded teaspoonfuls about 2 inches apart onto ungreased baking sheet. Bake 8 to 10 minutes or until light brown. About 6 1/2 dozen cookies. ",
                        "http://s3.amazonaws.com/gmi-digital-library/65caecf7-a8f7-4a09-8513-2659cf92871e.jpg",
                        0)
        );

        recetaList.add(
                new Receta(
                        "Mic\'s Yorkshire Puds",
                        (int)(Math.random()*10) + 1,
                        "200g plain flour Baking3 eggs Dairy300ml milk Dairy3 tbsp vegetable oil Condiments",
                        "Put the flour and some seasoning into a large bowl. Stir in eggs  one at a time. Whisk in milk until you have a smooth batter. Chill in the fridge for at least 30 minutes. Heat oven to 220C/gas mark 7. Pour the oil into the holes of a 8-hole muffin tin. Heat tin in the oven for 5 minutes. Ladle the batter mix into the tin. Bake for 30 minutes until well browned and risen. ",
                        "http://upload.wikimedia.org/wikipedia/commons/f/f9/Yorkshire_Pudding.jpg",
                        1)
        );

        insertRecetas( recetaList );
    }


    public Cursor getRecetaById(Receta receta)
    {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "SELECT * FROM " + RecetaContract.RecetaEntry.TABLE_NAME +
                " WHERE " + RecetaContract.RecetaEntry.COLUMN_ID + " = ?";
        String [] selectionArgs = { receta.id};

        return db.rawQuery(sql, selectionArgs);
    }


    public List <Receta> getListRecetas(Receta receta)
    {
        SQLiteDatabase db = database.getReadableDatabase();

//        String sql = "SELECT * FROM " + RecetaContract.RecetaEntry.TABLE_NAME;

        String [] selectionArgs = {
                receta.id,
                receta.nombre,
                String.valueOf(receta.personas),
                receta.descripcion,
                receta.preparacion,
                receta.image,
                String.valueOf(receta.fav)
        };

        Cursor cursor = db.query(
                RecetaEntry.TABLE_NAME,
                RecetaContract.SQL_PROJECTION_RECETA,
                RecetaContract.SQL_FILTER_QUERY_RECETA,
                selectionArgs,
                null,
                null,
                null
        );

        List < Receta > recetaList = new ArrayList < Receta > ();

        Receta recetaTemp;
        while(cursor.moveToNext())
        {
            recetaTemp = new Receta(  );
            recetaTemp.id = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_ID) );
            recetaTemp.nombre = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_NOMBRE ) );
            recetaTemp.personas= cursor.getInt( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_PERSONAS) );
            recetaTemp.descripcion = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_DESCRIPCION) );
            recetaTemp.preparacion = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_PREPARACION) );
            recetaTemp.image = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_IMAGE) );
            recetaTemp.fav = cursor.getInt(cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_FAV) );

            recetaList.add( recetaTemp );
        }

        cursor.close();
        return recetaList;
    }

    public List <Receta> getListRecetas()
    {
        SQLiteDatabase db = database.getReadableDatabase();

        String sql = "SELECT * FROM " + RecetaContract.RecetaEntry.TABLE_NAME;

        Cursor cursor = db.rawQuery( sql, null );

        List < Receta > recetaList = new ArrayList < Receta > ();
        Receta receta;

        while(cursor.moveToNext())
        {
            receta = new Receta(  );
            receta.id = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_ID) );
            receta.nombre = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_NOMBRE ) );
            receta.personas= cursor.getInt( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_PERSONAS) );
            receta.descripcion = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_DESCRIPCION) );
            receta.preparacion = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_PREPARACION) );
            receta.image = cursor.getString( cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_IMAGE) );
            receta.fav = cursor.getInt(cursor.getColumnIndexOrThrow( RecetaEntry.COLUMN_FAV) );

            recetaList.add( receta );
        }

        cursor.close();

        DatabaseUtils.dumpCursor( getRecetas() );

        return recetaList;
    }


    public Cursor getRecetas()
    {
        SQLiteDatabase db = database.getReadableDatabase();

        String sql = "SELECT * FROM " + RecetaContract.RecetaEntry.TABLE_NAME;

        return db.rawQuery( sql, null );
    }

    public boolean updateReceta(Receta receta)
    {
        SQLiteDatabase db = database.getWritableDatabase();

        String whereClause = RecetaContract.RecetaEntry.COLUMN_ID +  " = ?";
        String [] whereArgs = { receta.id };

        ContentValues values = new ContentValues(  );
        values.put( RecetaContract.RecetaEntry.COLUMN_NOMBRE, receta.nombre );
        values.put( RecetaContract.RecetaEntry.COLUMN_PERSONAS, receta.personas );
        values.put( RecetaContract.RecetaEntry.COLUMN_DESCRIPCION, receta.descripcion );
        values.put( RecetaContract.RecetaEntry.COLUMN_PREPARACION, receta.preparacion );
        values.put( RecetaContract.RecetaEntry.COLUMN_IMAGE, receta.image );
        values.put( RecetaContract.RecetaEntry.COLUMN_FAV, receta.fav );

        return db.update( RecetaContract.RecetaEntry.TABLE_NAME,
                values,
                whereClause,
                whereArgs) > 0;

    }

    public boolean deleteReceta(Receta receta)
    {
        SQLiteDatabase db = database.getWritableDatabase();

        String whereClause = RecetaContract.RecetaEntry.COLUMN_ID +  " = ?";
        String [] whereArgs = { receta.id };

        return db.delete( RecetaContract.RecetaEntry.TABLE_NAME,
                whereClause,
                whereArgs) > 0;

    }

    public boolean deleteReceta(String recetaId)
    {
        SQLiteDatabase db = database.getWritableDatabase();

        String whereClause = RecetaContract.RecetaEntry.COLUMN_ID +  " = ?";
        String [] whereArgs = { recetaId };

        return db.delete( RecetaContract.RecetaEntry.TABLE_NAME,
                whereClause,
                whereArgs) > 0;

    }


}
